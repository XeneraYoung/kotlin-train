package handler

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import util.handleJsonErrors
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.lang.reflect.Type
import java.nio.charset.StandardCharsets
import javax.ws.rs.Consumes
import javax.ws.rs.Produces
import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.MultivaluedMap
import javax.ws.rs.ext.MessageBodyReader
import javax.ws.rs.ext.MessageBodyWriter
import javax.ws.rs.ext.Provider

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class JsonMessageHandler : MessageBodyWriter<Any>, MessageBodyReader<Any> {
    private val json = Json { encodeDefaults = true }

    override fun isWriteable(
        `class`: Class<*>?,
        type: Type?,
        annotations: Array<Annotation>?,
        mediaType: MediaType?
    ): Boolean {
        return true
    }

    override fun getSize(
        `object`: Any?,
        `class`: Class<*>?,
        type: Type?,
        annotations: Array<Annotation>?,
        mediaType: MediaType?
    ): Long {
        return -1
    }

    @ExperimentalSerializationApi
    @Throws(IOException::class, WebApplicationException::class)
    override fun writeTo(
        entity: Any,
        type: Class<*>,
        genericType: Type?,
        annotations: Array<Annotation>?,
        mediaType: MediaType?,
        headers: MultivaluedMap<String, Any>?,
        out: OutputStream
    ) {
        val string: String = json.encodeToString(serializer(genericType ?: type), entity)

        OutputStreamWriter(out, StandardCharsets.UTF_8).use { writer ->
            writer.write(string)
        }
    }

    override fun isReadable(
        `class`: Class<*>,
        type: Type,
        annotations: Array<Annotation>,
        mediaType: MediaType
    ): Boolean {
        return true
    }

    @ExperimentalSerializationApi
    @Throws(IOException::class, WebApplicationException::class)
    override fun readFrom(
        type: Class<Any>,
        genericType: Type?,
        annotations: Array<Annotation>?,
        contentType: MediaType?,
        headers: MultivaluedMap<String, String>?,
        input: InputStream
    ): Any {
        val string = input.bufferedReader().use(BufferedReader::readText)
        return handleJsonErrors() {
            Json.decodeFromString(serializer(genericType ?: type), string)
        }
    }
}

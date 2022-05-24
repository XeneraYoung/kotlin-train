package handler

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import util.ResponseError
import util.ServerError
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Serializable
data class ResponseErrorEntity(
    val message: String,
    val code: Int,
    val type: String?,
    val field: String?
)

@Provider
class ErrorHandler : ExceptionMapper<Exception> {
    override fun toResponse(e: Exception): Response {
        val error: ResponseError = if (e is ResponseError) {
            e
        } else {
            ServerError("Internal server error.")
        }

        return Response
            .status(error.code)
            .entity(
                Json.encodeToString
                    (
                    ResponseErrorEntity.serializer(),
                    ResponseErrorEntity(
                        message = error.message,
                        code = error.code,
                        type = error::class.simpleName,
                        field = error.field
                    )
                )
            )
            .build()
    }
}

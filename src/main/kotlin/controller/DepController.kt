package controller

import service.DepService
import util.genericEntity
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/deps")
@Produces(MediaType.APPLICATION_JSON)
class DepController() {
    private val depService = DepService()

    @GET
    @Path("/all")
    fun getAll(): Response {
        try {
            val records = depService.getAll()
            println(records)
            return Response.status(Response.Status.OK).genericEntity(records).build()
        } catch (e: Exception) {
            println(e.message)
            throw RuntimeException(e.message)
        }
    }
}
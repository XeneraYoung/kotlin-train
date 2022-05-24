import controller.DepController
import filter.CORSFilter
import handler.ErrorHandler
import handler.JsonMessageHandler
import javax.ws.rs.ApplicationPath
import javax.ws.rs.core.Application

@ApplicationPath("/")
class App : Application() {
    private val singletons = setOf(
        DepController(),

        // filter
        CORSFilter(),
        // handler
        ErrorHandler(),
        JsonMessageHandler()
    )

    override fun getSingletons(): Set<Any> {
        return singletons
    }
}
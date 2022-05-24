import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
// import javax.script.ScriptEngine
// import javax.script.ScriptEngineManager
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener
import javax.servlet.annotation.WebListener

@WebListener
class Server : ServletContextListener {
    private lateinit var datasource: HikariDataSource

    override fun contextInitialized(sce: ServletContextEvent?) {
        super.contextInitialized(sce)

        datasource = HikariDataSource()
        datasource.jdbcUrl = "jdbc:postgresql://localhost:6543/postgres"
        datasource.username = "test_db"
        datasource.password = "test_db"
        datasource.driverClassName = "org.postgresql.Driver"
        datasource.addDataSourceProperty("reWriteBatchedInserts", true)
        sce?.servletContext?.setAttribute("ds", datasource)

        val database = Database.connect(datasource)
        sce?.servletContext?.setAttribute("db", database)

        // val engine = ScriptEngineManager().getEngineByExtension("kts")
        // engine.eval("true")
    }

    override fun contextDestroyed(sce: ServletContextEvent?) {
        super.contextDestroyed(sce)
        datasource.close()
    }
}
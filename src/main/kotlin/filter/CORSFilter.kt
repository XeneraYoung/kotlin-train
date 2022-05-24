package filter

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebFilter(filterName = "CORSFilter", urlPatterns = ["/*"])
class CORSFilter : Filter {
    override fun init(config: FilterConfig?) {
    }

    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, chain: FilterChain) {
        val request = servletRequest as HttpServletRequest
        val resp = servletResponse as HttpServletResponse

        resp.addHeader("Access-Control-Allow-Origin", "*")
        resp.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS")
        resp.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept")

        // Just ACCEPT and REPLY OK if OPTIONS
        if (request.method == "OPTIONS") {
            resp.status = HttpServletResponse.SC_OK
            return
        }

        chain.doFilter(request, servletResponse)
    }

    override fun destroy() {
    }
}
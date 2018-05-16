package be.jstack.ticketing.util.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CorsFilter implements Filter {

    // This is to be replaced with a list of domains allowed to access the server
    private final List<String> allowedOrigins = Arrays.asList("http://localhost:8081", "http://127.0.0.1:8081", "http://localhost:4200", "http://127.0.0.1:4200");

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        // Lets make sure that we are working with HTTP (that is, against HttpServletRequest and HttpServletResponse objects)
        if (req instanceof HttpServletRequest && res instanceof HttpServletResponse) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;

            // Access-Control-Allow-Origin
            String origin = request.getHeader("Origin");
            response.setHeader("Access-Control-Allow-Origin", allowedOrigins.contains(origin) ? origin : "");
            response.setHeader("Vary", "Origin");

            // Access-Control-Max-Age
            response.setHeader("Access-Control-Max-Age", "3600");

            // Access-Control-Allow-Credentials
            response.setHeader("Access-Control-Allow-Credentials", "true");

            // Access-Control-Allow-Methods
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, OPTIONS, DELETE");

            // Access-Control-Expose-Headers
            response.setHeader("Access-Control-Expose-Headers", "Authorization");

            // Access-Control-Allow-Headers
            response.setHeader("Access-Control-Allow-Headers",
                    "Origin, X-Requested-With, X-Auth-Token, Authorization, Content-Type, Accept, x-ijt");
        }
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {
    }
}
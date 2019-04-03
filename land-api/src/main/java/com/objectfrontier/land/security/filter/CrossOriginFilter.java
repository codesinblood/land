package com.objectfrontier.land.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hariraj.sampath
 * @since v1.0
 */
public class CrossOriginFilter implements Filter {

    @Autowired
    private Environment env;

    private List<String> allowedOrigins = new ArrayList<String>();
    // preflight cache duration in the browser
    private String maxAge = "600"; // 600 seconds = 10 minutes
    private String allowedMethods = "GET,POST,PUT,DELETE";
    private String allowedHeaders = "X-CSRF-TOKEN,Content-Type,Accept,Origin";
    public CrossOriginFilter(Environment environment) throws ServletException {
        String origins = env.getRequiredProperty("allowed.origins", String.class);
        this.setAllowedOrigins(origins);
    }

    public void destroy() {
    }

    public void init(FilterConfig config) {
    }

    public void setAllowedOrigins(String origins) {
        allowedOrigins.clear();
        allowedOrigins.addAll(Arrays.asList(origins.split(",")));
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        handle((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void handle(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String origin = request.getHeader("Origin");
        if (origin != null && allowedOrigins.contains(origin)) {
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setHeader("Access-Control-Allow-Credentials", "true");
            if (isPreflightRequest(request)) {
                response.setHeader("Access-Control-Max-Age", maxAge);
                response.setHeader("Access-Control-Allow-Methods", allowedMethods);
                response.setHeader("Access-Control-Allow-Headers", allowedHeaders);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private boolean isPreflightRequest(HttpServletRequest request) {
        return "OPTIONS".equalsIgnoreCase(request.getMethod())
                && request.getHeader("Access-Control-Request-Method") != null;
    }
}

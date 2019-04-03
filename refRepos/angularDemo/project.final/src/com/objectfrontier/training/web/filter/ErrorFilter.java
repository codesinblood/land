package com.objectfrontier.training.web.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;

import com.objectfrontier.training.web.exceptions.AppException;
import com.objectfrontier.training.web.servlet.JsonConverter;

public class ErrorFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        log("%s", "Error Filter  started");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        response.setContentType(ContentType.APPLICATION_JSON.toString());
        response.setHeader("Access-Control-Allow-Origin",  "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "*");
        PrintWriter out=response.getWriter();

        try {
            chain.doFilter(request, response);
            response.setStatus(HttpStatus.SC_OK);
        } catch (Exception e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            if(e instanceof AppException) {
                out.write(JsonConverter.toJson(((AppException) e).getErrorCodes()));
            } else {
                e.printStackTrace();
            }
            log("%s", "Error Filter  Ended");
        }
    }

    @Override
    public void destroy() {}

    private static void log(String format, Object args) {
        System.out.format(format + "\n", args);
    }


}

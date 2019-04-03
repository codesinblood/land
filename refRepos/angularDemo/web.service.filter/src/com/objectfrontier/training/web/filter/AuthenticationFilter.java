package com.objectfrontier.training.web.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.objectfrontier.training.web.exceptions.AppException;
import com.objectfrontier.training.web.exceptions.ErrorCode;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        log("%s", "AuthenticationFilter  Started");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        if (Objects.isNull(session)) { // Check the Session is valid.
            throw new AppException(ErrorCode.UN_AUTHENTICATED);
        } else {
            chain.doFilter(request, response);
        }
        log("%s", "AuthenticationFilter  Ended");
    }

    @Override
    public void destroy() {}

    private static void log(String format, Object args) {
        System.out.format(format + "\n", args);
    }
}

package com.objectfrontier.training.web.filter;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.objectfrontier.training.web.connectionManager.ConnectionManager;
import com.objectfrontier.training.web.service.ObjectFactory;

public class TransactionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log("%s", "TransactionFilter  Started");
        ConnectionManager connectionManager = null;
        Connection connection = null;
        try {
            connectionManager = ObjectFactory.getConnectionManager();
            connectionManager.createConnection(); // Create the Connection in ThreadLocal
            connection = ConnectionManager.threadConnection.get(); // Get the connection from ThreadLocal
            chain.doFilter(request, response);
            connectionManager.release(connection, true); // Release the connection.
        } catch (Exception e) {
            connectionManager.release(connection, false);
            throw e;
        } finally {
            ConnectionManager.threadConnection.remove();
        }
        log("%s", "TransactionFilter  Ended");
    }

    @Override
    public void destroy() {}

    private static void log(String format, Object args) {
        System.out.format(format + "\n", args);
    }
}

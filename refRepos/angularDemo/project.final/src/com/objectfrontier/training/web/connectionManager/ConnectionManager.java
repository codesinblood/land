package com.objectfrontier.training.web.connectionManager;

import java.sql.Connection;
import java.sql.SQLException;

import com.objectfrontier.training.web.exceptions.AppException;
import com.objectfrontier.training.web.exceptions.ErrorCode;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionManager {

    private static HikariDataSource dataSource;
    public static ThreadLocal<Connection> threadConnection = new ThreadLocal<>();

    static {

        HikariConfig config = new HikariConfig("/hikari.properties");
        config.setMaximumPoolSize(2);
        dataSource = new HikariDataSource(config);
    }

    public void createConnection() {
        threadConnection.set(get());
    }

    public Connection get() throws AppException {

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER);
        }

        return connection;
    }

    public void release(Connection connection, boolean flag) {

        try {
            if(flag == true) {
                connection.commit();
            } else {
                connection.rollback();
            }
            connection.close();
        } catch (SQLException e) {
            throw new AppException(ErrorCode.SERVER);
        }
    }

    private static void log(String format, Object args) {

        System.out.format(format + "\n", args);
    }
}

package com.objectfrontier.training.jdbc.connectionManager;

import java.sql.Connection;
import java.sql.SQLException;

import com.objectfrontier.training.jdbc.exceptions.AppException;
import com.objectfrontier.training.jdbc.exceptions.ErrorCode;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionManager {

    private static HikariDataSource dataSource;

    static {

        HikariConfig config = new HikariConfig("/hikari.properties");
        config.setMaximumPoolSize(2);
        dataSource = new HikariDataSource(config);
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
//            log("%s", e.getMessage());
            throw new AppException(ErrorCode.SERVER);
        }
    }

    private static void log(String format, Object args) {

        System.out.format(format + "\n", args);
    }

    public static void main(String[] args) {

        try {
        ConnectionManager c = new ConnectionManager();
        Connection conn = c.get();
        System.out.println(conn);
        c.release(conn, false);
        System.out.println("Hiiii");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}

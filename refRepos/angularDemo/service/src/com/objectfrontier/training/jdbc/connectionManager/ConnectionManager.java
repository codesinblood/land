package com.objectfrontier.training.jdbc.connectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionManager {

    private static HikariDataSource dataSource;

    static {

        HikariConfig config = new HikariConfig("res\\dbconfig.properties");
        config.setMaximumPoolSize(5);
        dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException, IOException {

        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        return connection;
    }

    public void releaseConnection(Connection connection, boolean flag) throws SQLException {

        if(flag == true) {
            connection.commit();
        } else {
            connection.rollback();
        }
        connection.close();
    }
}

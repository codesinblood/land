package com.objectfrontier.training.java.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    public Connection getConnection() throws SQLException {

        String url = "jdbc:mysql://pc1620:3306/vinoth_ari?useSSL=true";
        String user = "vinoth_ari";
        String password = "demo";
        Connection con = DriverManager.getConnection(url, user, password);
        return con;
    }

    public void release(Connection connection) throws SQLException {

        connection.close();
    }
}

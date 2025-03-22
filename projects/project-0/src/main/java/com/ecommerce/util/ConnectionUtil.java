package com.ecommerce.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    private static Connection conn = null;

    private ConnectionUtil() {
    }

    public static Connection getConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                return conn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "secret";
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not establish connection");
        }

        return conn;
    }
}

package com.enigma.configure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private static Connection conn;
    public Connection startConnection() {
        String url = "jdbc:postgresql://localhost:5432/db_bioskop";
        String username = System.getenv("USERNAME_DB");
        String password = System.getenv("PASSWORD_DB");
        try {
            conn = DriverManager.getConnection(url, username, password);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}

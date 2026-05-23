package com.hospital.hms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Read from system environment variables
    private static final String URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Could not find PostgreSQL Driver!");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        // Validation to give you a helpful warning if you forgot to set your variables locally
        if (URL == null || USER == null || PASSWORD == null) {
            throw new SQLException("❌ Database environment variables (DB_URL, DB_USER, DB_PASSWORD) are not set!");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
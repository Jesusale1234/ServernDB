package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Connects the Java program with a MySQL database.
 */
public class DatabaseConnector {

    // We include the URL of the database.
    private static final String URL = "jdbc:mysql://localhost:3306/scholar_db";

    // We include an DB admin credentials to log in into the environment and to be able to make changes into the database.
    private static final String USER = "jesus";
    private static final String PASSWORD = "Byyisusxd123.";

    // We use the getConnection() function to connect to the MySQL database.
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("Connection error with MySQL: " + e.getMessage());
        }
    }

}

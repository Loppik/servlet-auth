package com.alex.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnection implements IDBConnection {
    private static PostgreSQLConnection instance;
    private Connection connection;

    private PostgreSQLConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/english_coup", "postgres", "123");
            System.out.println("Connected to PostgreSQL database!");

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC driver not found.");
            e.printStackTrace();
        }
    }

    public static PostgreSQLConnection getInstance() {
        if (instance == null)
            instance = new PostgreSQLConnection();
        return instance;
    }

    public Connection connect() {
        return connection;
    }
}

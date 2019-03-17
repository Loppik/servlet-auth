package com.alex.dao;

import com.alex.model.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSqlDao extends Dao {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    public PostgreSqlDao()  {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/english_coup", "postgres", "123");
            System.out.println("Connected to PostgreSQL database!");

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC driver not found.");
            e.printStackTrace();
        }
    }

    public User getUserByEmail(String email) {
        User user = null;
        try {
            stmt = conn.createStatement();
            String query = String.format("SELECT * FROM users WHERE email = '%s'", email);
            rs = stmt.executeQuery(query);
            rs = stmt.getResultSet();
            while(rs.next()) {
                user = new User(rs.getInt("user_id"), rs.getString("email"), rs.getString("password"));
            }
        } catch (SQLException e) {
            sqlExceptionOutput(e);
        } finally {
            closeStatement();
            closeResultSet();
        }
        return user;
    }

    public void addUser(User user) {
        try {
            stmt = conn.createStatement();
            String query = String.format("INSERT INTO users (email, password) VALUES ('%s', '%s')", user.getEmail(), user.getPassword());
            stmt.executeQuery(query);
        } catch (SQLException e) {
            sqlExceptionOutput(e);
        } finally {
            closeStatement();
            closeResultSet();
        }
    }

    private void closeStatement() {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException sqlEx) { } // ignore

            stmt = null;
        }
    }

    private void closeResultSet() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException sqlEx) { } // ignore
            rs = null;
        }
    }

    private void sqlExceptionOutput(SQLException e) {
        System.out.println("SQLException: " + e.getMessage());
        System.out.println("SQLState: " + e.getSQLState());
        System.out.println("VendorError: " + e.getErrorCode());
    }
}

package com.alex.dao;

import com.alex.model.IDBConnection;
import com.alex.model.PostgreSQLConnection;
import com.alex.model.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao implements IUserDao {
    private IDBConnection c = PostgreSQLConnection.getInstance();
    private Connection conn = c.connect();
    private Statement stmt = null;
    private ResultSet rs = null;

    public User getByEmail(String email) {
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

    public void add(User user) {
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

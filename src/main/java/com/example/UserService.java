package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import java.util.logging.Logger;

public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
    // SECURITY ISSUE: Hardcoded credentials
    private String password = "admin123";

    // VULNERABILITY: SQL Injection
    public void findUser(String username) throws java.sql.SQLException {

        String query =
            "SELECT * FROM users WHERE name = '" + username + "'";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/db",
                    "root", password);
             Statement st = conn.createStatement()) {

            st.executeQuery(query);
        }
    }

    // SMELL: Unused method
    public void notUsed() {
        LOGGER.info("I am never called");
    }
    // EVEN WORSE: another SQL injection 
public void deleteUser(String username) throws java.sql.SQLException { 
    String query = 
        "DELETE FROM users WHERE name = '" + username + "'"; 
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/db", "root", password);
         Statement st = conn.createStatement()) {
        st.execute(query); 
    }
} 
}

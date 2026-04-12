package com.student.management;


import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class AdminService {

    // Method to create a new admin with hashed password
    public void registerAdmin(String username, String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        String query = "INSERT INTO admins (username, password) VALUES (?, ?)" +"ON DUPLICATE KEY UPDATE password=VALUES(password)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, hashedPassword);
            ps.executeUpdate();
            System.out.println("✅ Admin registered successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean login(String username, String password) {
        String query = "SELECT password FROM admins WHERE username = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                return BCrypt.checkpw(password, hashedPassword);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
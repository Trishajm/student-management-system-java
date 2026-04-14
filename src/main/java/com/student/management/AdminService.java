package com.student.management;


import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.Scanner;

public class AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

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
// logging
    public boolean login(Scanner sc) {
        System.out.println("\n===== ADMIN LOGIN =====");
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        String query = "SELECT password FROM admins WHERE username = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");

                if (BCrypt.checkpw(password, storedHash)) {
                    logger.info("Admin '{}' logged in successfully.", username);
                    System.out.println("✅ Login successful!");
                    return true;
                }
            }

            logger.warn("Failed login attempt for username '{}'.", username);
            System.out.println("❌ Invalid credentials.");
        } catch (Exception e) {
            logger.error("Error during admin login.", e);
        }
        return false;
    }
}
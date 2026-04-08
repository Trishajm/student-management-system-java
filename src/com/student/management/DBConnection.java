package com.student.management;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        Connection con = null;

        try {
            String url = "jdbc:mysql://localhost:3306/student_db";
            String user = "root";
            String password = "Root@2005"; // change this

            con = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }

        return con;
    }
}
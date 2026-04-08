package com.student.management;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class StudentService {
    private ArrayList<Student> students = new ArrayList<>();

        // Add student

    public void addStudent(Scanner sc) {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Course: ");
            String course = sc.nextLine();

            System.out.print("Enter Branch: ");
            String branch = sc.nextLine();

            System.out.print("Enter Marks: ");
            int marks = sc.nextInt();

            String query = "INSERT INTO students VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, course);
            ps.setString(4, branch);
            ps.setInt(5, marks);

            ps.executeUpdate();

            System.out.println("✅ Student added to database!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        // View all students

    public void viewAllStudents() {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM students";
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                ", Name: " + rs.getString("name") +
                                ", Course: " + rs.getString("course") +
                                ", Branch: " + rs.getString("branch") +
                                ", Marks: " + rs.getInt("marks")
                );
            }

            if (!found) {
                System.out.println("⚠️ No students found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        // Search student by ID
        public void searchStudentById(Scanner sc) {
            try {
                Connection con = DBConnection.getConnection();

                System.out.print("Enter ID to search: ");
                int id = sc.nextInt();

                String query = "SELECT * FROM students WHERE id = ?";
                PreparedStatement ps = con.prepareStatement(query);

                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    System.out.println(
                            "ID: " + rs.getInt("id") +
                                    ", Name: " + rs.getString("name") +
                                    ", Course: " + rs.getString("course") +
                                    ", Branch: " + rs.getString("branch") +
                                    ", Marks: " + rs.getInt("marks")
                    );
                } else {
                    System.out.println("❌ Student not found.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    // Update student details
    public void updateStudent(Scanner sc) {
        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();

            // Check if student exists
            String checkQuery = "SELECT * FROM students WHERE id = ?";
            PreparedStatement checkPs = con.prepareStatement(checkQuery);
            checkPs.setInt(1, id);
            ResultSet rs = checkPs.executeQuery();

            if (!rs.next()) {
                System.out.println("❌ Student not found.");
                return;
            }

            // Take new values
            System.out.print("Enter new Name: ");
            String name = sc.nextLine();

            System.out.print("Enter new Course: ");
            String course = sc.nextLine();

            System.out.print("Enter new Branch: ");
            String branch = sc.nextLine();

            System.out.print("Enter new Marks: ");
            int marks = sc.nextInt();

            // Update query
            String query = "UPDATE students SET name=?, course=?, branch=?, marks=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, course);
            ps.setString(3, branch);
            ps.setInt(4, marks);
            ps.setInt(5, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Student updated successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Delete student
    public void deleteStudent(Scanner sc) {
        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter ID to delete: ");
            int id = sc.nextInt();

            String query = "DELETE FROM students WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Student deleted successfully!");
            } else {
                System.out.println("❌ Student not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    }

package com.student.management;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
public class StudentService {
    private ArrayList<Student> students = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
        // Add student

    public void addStudent(Scanner sc) {
        try {
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

            String query = "INSERT INTO students (id, name, course, branch, marks) VALUES (?, ?, ?, ?, ?)";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(query)) {

                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setString(3, course);
                ps.setString(4, branch);
                ps.setInt(5, marks);
                ps.executeUpdate();

                logger.info("Student added successfully with ID: {}", id);
                System.out.println("✅ Student added to database!");
            }
        } catch (Exception e) {
            logger.error("Error while adding student.", e);
            System.out.println("❌ Failed to add student.");
        }
    }

        // View all students

    public void viewAllStudents() {
        String query = "SELECT * FROM students";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            logger.info("Fetching all student records");

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Course: " + rs.getString("course") +
                        ", Branch: " + rs.getString("branch") +
                        ", Marks: " + rs.getInt("marks"));
            }

            logger.info("Student records fetched successfully");

        } catch (Exception e) {
            logger.error("Error while fetching student records", e);
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
        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter New Name: ");
        String name = sc.nextLine();

        System.out.print("Enter New Course: ");
        String course = sc.nextLine();

        System.out.print("Enter New Branch: ");
        String branch = sc.nextLine();

        System.out.print("Enter New Marks: ");
        int marks = sc.nextInt();

        String query = "UPDATE students SET name=?, course=?, branch=?, marks=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setString(2, course);
            ps.setString(3, branch);
            ps.setInt(4, marks);
            ps.setInt(5, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Student updated successfully!");
                logger.info("Student with ID {} updated successfully", id);
            } else {
                System.out.println("❌ Student not found.");
                logger.warn("Update failed. Student with ID {} not found", id);
            }

        } catch (Exception e) {
            logger.error("Error while updating student with ID {}", id, e);
        }
    }


    // Delete student
    public void deleteStudent(Scanner sc) {
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();

        String query = "DELETE FROM students WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Student deleted successfully!");
                logger.info("Student with ID {} deleted successfully", id);
            } else {
                System.out.println("❌ Student not found.");
                logger.warn("Delete failed. Student with ID {} not found", id);
            }

        } catch (Exception e) {
            logger.error("Error while deleting student with ID {}", id, e);
        }
    }

    // seacrch student by name

    public void searchStudentByName(Scanner sc) {
        System.out.print("Enter name to search: ");
        String name = sc.nextLine();

        if(name.isEmpty())
        {
            System.out.println("name cannot be empty. Please enter a valid name");
            return;
        }

        String query = "SELECT * FROM students WHERE name LIKE ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, "%" + name + "%");
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
                System.out.println("No students found with that name.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //filter student by branch

    public void filterStudentsByBranch(Scanner sc) {
        System.out.print("Enter branch (e.g., CSE): ");
        String branch = sc.nextLine();

        String query = "SELECT * FROM students WHERE branch = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, branch);
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
                System.out.println("No students found for this branch.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //sort student ny marks
    public void sortStudentsByMarks() {
        String query = "SELECT * FROM students ORDER BY marks DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                ", Name: " + rs.getString("name") +
                                ", Course: " + rs.getString("course") +
                                ", Branch: " + rs.getString("branch") +
                                ", Marks: " + rs.getInt("marks")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    }

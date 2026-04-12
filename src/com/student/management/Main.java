package com.student.management;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();
        AdminService adminService = new AdminService();
        //adminService.registerAdmin("admin","admin123");

        boolean isAuthenticated = false;
        int attempts = 3;

        while (attempts > 0) {
            System.out.println("\n===== ADMIN LOGIN =====");
            System.out.print("Enter Username: ");
            String username = sc.nextLine();

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            if (adminService.login(username, password)) {
                System.out.println("✅ Login successful!");
                isAuthenticated = true;
                break;
            } else {
                attempts--;
                System.out.println("❌ Invalid credentials. Attempts left: " + attempts);
            }
        }

        if (!isAuthenticated) {
            System.out.println("🚫 Too many failed attempts. Exiting...");
            sc.close();
            return;
        }
        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            // Handle invalid numeric input
            while (!sc.hasNextInt()) {
                System.out.println("⚠️ Please enter a valid number.");
                sc.next(); // discard invalid input
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    service.addStudent(sc);
                    break;

                case 2:
                    service.viewAllStudents();
                    break;

                case 3:
                   service.searchStudentById(sc);
                    break;

                case 4:
                    service.updateStudent(sc);
                    break;

                case 5:
                    service.deleteStudent(sc);
                    break;

                case 6:
                    System.out.println(" Exiting system. Goodbye!");
                    break;

                default:
                    System.out.println(" Invalid choice. Try again.");
            }

        } while (choice != 6);

        sc.close();
    }
}
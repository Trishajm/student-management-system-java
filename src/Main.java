package com.student.management;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();

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

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    service.addStudent(sc);
                    break;

                case 2:
                    service.viewAllStudents();
                    break;

                case 3:
                    System.out.print("Enter ID to search: ");
                    int id = sc.nextInt();
                    Student s = service.searchById(id);

                    if (s == null) {
                        System.out.println(" Student not found.");
                    } else {
                        System.out.println(s);
                    }
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
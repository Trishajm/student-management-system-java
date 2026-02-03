package com.student.management;
import java.util.ArrayList;
import java.util.Scanner;
public class StudentService {
    private ArrayList<Student> students = new ArrayList<>();

        // Add student
        public void addStudent(Scanner sc) {
            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // consume newline

            if(isIdExists(id))
            {
                System.out.println("Students with this id already exists!");
                return;
            }
            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            if(name.trim().isEmpty())
            {
                System.out.println("Name cannot be empty!");
                return;
            }
            System.out.print("Enter Course: ");
            String course = sc.nextLine();

            System.out.print("Enter Branch: ");
            String branch = sc.nextLine();

            System.out.print("Enter Marks: ");
            int marks = sc.nextInt();

            if(marks<0 || marks>100)
            {
                System.out.println("Marks must be between 0 and 100");
                return;
            }
            Student s = new Student(id, name, course, branch, marks);
            students.add(s);

            System.out.println("✅ Student added successfully!");
        }

        // View all students
        public void viewAllStudents() {
            if (students.isEmpty()) {
                System.out.println(" No students found.");
                return;
            }

            for (Student s : students) {
                System.out.println(s);
            }
        }

        // Search student by ID
        public Student searchById(int id) {
            for (Student s : students) {
                if (s.getId() == id) {
                    return s;
                }
            }
            return null;
        }
    // Update student details
    public void updateStudent(Scanner sc) {
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        Student s = searchById(id);

        if (s == null) {
            System.out.println(" Student not found.");
            return;
        }

        System.out.print("Enter new Name: ");
        String name=sc.nextLine();
        if(name.trim().isEmpty())
        {
            System.out.println("Name cannot be empty!");
            return;
        }
        s.setName(sc.nextLine());


        System.out.print("Enter new Course: ");
        s.setCourse(sc.nextLine());

        System.out.print("Enter new Branch: ");
        s.setBranch(sc.nextLine());

        System.out.print("Enter new Marks: ");
        int marks=sc.nextInt();
        if(marks<0 ||marks>100)
        {
            System.out.println("Marks must be between 0 and 100");
            return;
        }
        s.setMarks(sc.nextInt());

        System.out.println(" Student updated successfully!");
    }

    // Delete student
    public void deleteStudent(Scanner sc) {
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();

        Student s = searchById(id);

        if (s == null) {
            System.out.println(" Student not found.");
            return;
        }

        students.remove(s);
        System.out.println(" Student deleted successfully!");
    }

    //Check if Id already exists
    public boolean isIdExists(int id)
    {
        for(Student s: students)
        {
            if(s.getId()==id)
            {
                return true;
            }
        }
        return false;
    }
    }

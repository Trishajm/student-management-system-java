# 🎓 Student Management System (Console-Based)

A **console-based Student Management System** developed in **Java** using a **layered architecture** and **MySQL database integration**. This application allows administrators to securely manage student records with full **CRUD operations** and **BCrypt-based authentication**.

---

## 📌 Features

### 🔐 Admin Authentication
- Secure admin login system.
- Passwords are **hashed using BCrypt**, ensuring real-world security.
- Protection against plain-text password storage.

### 👨‍🎓 Student Management (CRUD)
- ➕ Add new students.
- 📋 View all students.
- 🔍 Search students by ID.
- ✏️ Update student details.
- ❌ Delete student records.

### 🗄️ Database Integration
- Uses **MySQL** for persistent data storage.
- JDBC connectivity for database operations.
- Ensures data is retained even after the application exits.

### 🏗️ Layered Architecture
- **Model Layer** – `Student.java`
- **Service Layer** – `StudentService.java`, `AdminService.java`
- **Utility Layer** – `DBConnection.java`
- **Presentation Layer** – `Main.java`

---

## 🛠️ Technologies Used

| Technology | Purpose |
|-----------|---------|
| **Java** | Core programming language |
| **MySQL** | Database for persistent storage |
| **JDBC** | Database connectivity |
| **Maven** | Dependency management |
| **BCrypt** | Secure password hashing |
| **Git & GitHub** | Version control |

---

## 📁 Project Structure
StudentManagementSystem/
│
├── src/
│ └── com.student.management/
│ ├── Main.java
│ ├── Student.java
│ ├── StudentService.java
│ ├── AdminService.java
│ └── DBConnection.java
│
├── pom.xml
├── .gitignore
└── README.md


---

## 🗄️ Database Setup

 1️⃣ Create Database
```sql
CREATE DATABASE student_db;
USE student_db;

2️⃣ Create Students Table
CREATE TABLE students (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    course VARCHAR(50),
    branch VARCHAR(50),
    marks INT CHECK (marks BETWEEN 0 AND 100)
);

3️⃣ Create Admins Table
CREATE TABLE admins (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255) NOT NULL
);

---

### ⚙️ Maven Dependencies

Ensure the following dependencies are included in your pom.xml:

<dependencies>
    <!-- MySQL Connector -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>8.3.0</version>
    </dependency>

    <!-- BCrypt for Password Hashing -->
    <dependency>
        <groupId>org.mindrot</groupId>
        <artifactId>jbcrypt</artifactId>
        <version>0.4</version>
    </dependency>
</dependencies>

---

### 🔌 Database Configuration

Update the database credentials in DBConnection.java:

private static final String URL = "jdbc:mysql://localhost:3306/student_db";
private static final String USER = "root";
private static final String PASSWORD = "your_password";

---

### ▶️ How to Run the Project

1️⃣ Clone the Repository
git clone https://github.com/Trishajm/student-management-system-java.git
cd student-management-system-java
2️⃣ Build the Project
mvn clean install
3️⃣ Run the Application
mvn exec:java -Dexec.mainClass="com.student.management.Main"

Alternatively, you can run Main.java directly from your IDE.
---

###🔐 Default Admin Setup

To create the first admin, temporarily add the following line in Main.java and run the application once:

adminService.registerAdmin("admin", "admin123");

After execution, remove or comment out this line to prevent duplicate entries.
---

🖥️ Sample Console Menu
===== ADMIN LOGIN =====
Enter Username: admin
Enter Password: admin123

===== STUDENT MANAGEMENT SYSTEM =====
1. Add Student
2. View All Students
3. Search Student by ID
4. Update Student
5. Delete Student
6. Exit
---

### 🔒 Why BCrypt for Passwords?
Plain Text Passwords	BCrypt Hashed Passwords
Easily readable	Not reversible
Vulnerable to data breaches	Resistant to brute-force attacks
No salting	Automatic salting
Not suitable for production	Industry-standard security

🧪 Example Output
Enter your choice: 1
Enter ID: 1
Enter Name: Trisha
Enter Course: BE
Enter Branch: CSE
Enter Marks: 98
Student added to database!

---

### 🚀 Future Enhancements
🌐 Graphical User Interface (JavaFX/Swing)
🔍 Search by name or branch
📊 Sorting and filtering options
📄 Export data to CSV/PDF
👥 Role-based access (Admin/User)
🌍 REST API using Spring Boot
🤝 Contributing

Contributions are welcome! Feel free to fork the repository and submit pull requests.

📜 License

This project is developed for educational and portfolio purposes.

👩‍💻 Author

Trisha J M
📧 GitHub: https://github.com/Trishajm

⭐ Support

If you found this project helpful, please give it a ⭐ on GitHub!


---


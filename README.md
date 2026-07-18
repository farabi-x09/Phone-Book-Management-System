# 📞 Phone Book Management System

A desktop-based **Phone Book Management System** developed using **Java Swing**, **JDBC**, and **MySQL**.

This project was built as part of my Java learning journey to demonstrate desktop application development with database integration.

---

## 📸 Features

### Authentication
- User Registration
- User Login
- Username Validation
- Secure Database Authentication

### Dashboard
- Welcome Screen
- User Session Handling

### Database
- MySQL Integration
- JDBC Connection
- User & Contact Tables

### Upcoming Features
- Add Contact
- Edit Contact
- Delete Contact
- Search Contacts
- Favorite Contacts
- Logout

---

## 🛠️ Technologies Used

- Java
- Java Swing
- JDBC
- MySQL
- XAMPP
- VS Code
- Git & GitHub

---

## 📂 Project Structure

```
PhoneBook/
│
├── lib/
│   └── mysql-connector-j-9.7.0.jar
│
├── sql/
│   └── schema.sql
│
├── src/
│   ├── controller/
│   ├── dao/
│   ├── db/
│   ├── model/
│   ├── ui/
│   └── Main.java
│
├── README.md
└── .gitignore
```

---

## ⚙️ Requirements

- Java JDK 17 or later
- MySQL (XAMPP recommended)
- VS Code (or any Java IDE)

---

## 🚀 How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/YOUR_REPOSITORY.git
```

### 2. Open the Project

Open the project in VS Code.

### 3. Start MySQL

Open **XAMPP** and start:

- Apache
- MySQL

### 4. Create Database

Open **phpMyAdmin**.

Run:

```
sql/schema.sql
```

This will create:

- phonebook_db
- users table
- contacts table

### 5. Configure Database

Open:

```
src/db/DBConnection.java
```

Example:

```java
private static final String URL = "jdbc:mysql://localhost:3306/phonebook_db";
private static final String USER = "root";
private static final String PASSWORD = "";
```

Update these values if your MySQL username or password is different.

### 6. Run

Run:

```
Main.java
```

---

## 📖 Learning Objectives

This project demonstrates:

- Java Swing GUI Development
- Object-Oriented Programming
- MVC Architecture
- DAO Pattern
- JDBC Database Connectivity
- CRUD Operations
- Authentication System

---

## 👨‍💻 Author

**Farabi Ahmed**

GitHub: https://github.com/farabi-x09

---

## 📄 License

This project was developed for educational purposes.
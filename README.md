# producthub
ProductHub REST API using Spring Boot

## 📌 Overview
ProductHub is a RESTful API built using Spring Boot that performs CRUD operations on Product resources.  
The application includes JWT authentication, validation, global exception handling, and MySQL database integration.

---

## 🚀 Features

- CRUD Operations for Products
- JWT Authentication & Authorization
- Input Validation using Bean Validation
- Global Exception Handling
- MySQL Database
- Swagger API Documentation

---

## 🛠️ Technologies Used

- Java
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- MySQL
- Maven
- Swagger (OpenAPI)

---

## 🗄️ Database Setup

1. Create database in MySQL:

CREATE DATABASE producthub;

2. Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/producthub
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update

---

## 🔐 Authentication

1. Login using /auth/login
2. Get JWT token
3. Add token in header:

Authorization: Bearer <your-token>

---

## 📌 API Endpoints

GET    /products  
GET    /products/{id}  
POST   /products  
PUT    /products/{id}  
DELETE /products/{id}  

---

## 📖 Swagger UI

http://localhost:8080/swagger-ui.html

---

## ▶️ How to Run

mvn clean install  
mvn spring-boot:run

Application runs at:
http://localhost:8080


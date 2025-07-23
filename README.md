# Social-Media-Application

# 📱 Social Media Application - Backend

This is a **backend-only** project for a social media application built with **Spring Boot** and **MySQL**. It provides RESTful APIs for user registration, authentication, post management, likes, comments, and follow functionality. The project uses **JWT authentication**, **Spring Security**, and **Hibernate/JPA** for persistence.

---

## 🚀 Features

### 🔐 Authentication & Authorization
- User registration & login
- Secure JWT token generation and validation
- Password encryption using BCrypt
- Role-based access control (USER / ADMIN)

### 👤 User Management
- Update user profile (bio, profile image, etc.)
- Follow / unfollow other users
- View followers and following lists

### 📝 Post Management
- Create, view, update, delete posts
- Like and unlike posts
- Comment on posts

### 📁 Database Entities
- User
- Post
- Comment
- Like
- Follow

---

## 🛠️ Tech Stack

- **Java 17+**
- **Spring Boot**
- **Spring Security + JWT**
- **Hibernate / JPA**
- **MySQL**
- **Lombok**
- **Swagger (OpenAPI)**
- **Postman (API Testing)**

---

## 📦 Project Structure

```bash
src/
├── config/             # JWT filters, security config
├── controller/         # REST API endpoints
├── dto/                # Data Transfer Objects
├── entity/             # JPA entity classes
├── exception/          # Custom exception handling
├── repository/         # Spring Data JPA interfaces
├── service/            # Business logic layer
├── util/               # JWT utility classes

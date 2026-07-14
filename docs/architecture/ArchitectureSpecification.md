# Library Management System — Architecture

---

# Purpose

The **Library Management System** is a console-based Java application designed to demonstrate:

* Core Java
* Object-Oriented Programming (OOP)
* Clean Architecture
* Layered Design
* Collections Framework
* File Handling
* Exception Handling
* Interfaces
* Inheritance
* Abstraction
* Encapsulation
* Polymorphism

The architecture is intentionally **beginner-friendly** while remaining **professional and extensible**, allowing future migration to technologies such as JDBC, PostgreSQL, REST APIs, Spring Boot, a Node.js backend, or a React frontend.

---

# Overall Architecture

The project follows a **Layered Architecture**, where each layer has a single, well-defined responsibility.

```text
User
 │
 ▼
Menu Layer
 │
 ▼
Service Layer
 │
 ▼
Repository Layer
 │
 ▼
Storage Layer
 │
 ▼
Text Files
```

Each layer communicates only with the layer directly below it, resulting in a clean separation of responsibilities.

---

# Why Layered Architecture Is Suitable

This architectural style is well suited for the MVP because it keeps the project simple while demonstrating professional software design principles.

| Benefit           | Explanation                                                                                                               |
| ----------------- | ------------------------------------------------------------------------------------------------------------------------- |
| Beginner-friendly | Each layer has a clear and focused responsibility.                                                                        |
| Interview-ready   | Demonstrates separation of concerns and clean software architecture.                                                      |
| Maintainable      | Changes within one layer have minimal impact on unrelated layers.                                                         |
| Testable          | Business logic can be tested independently from console input and file handling.                                          |
| Scalable          | File-based persistence can later be replaced with JDBC or another persistence mechanism without affecting business logic. |

---

# Layer Responsibilities

Each layer has a clearly defined responsibility.

| Layer                | Responsibilities                                                                                                          |
| -------------------- | ------------------------------------------------------------------------------------------------------------------------- |
| **Menu Layer**       | Displays menus, reads user input, performs basic input validation, invokes services, and displays user-friendly messages. |
| **Service Layer**    | Implements business logic, validates business rules, coordinates repositories, and throws meaningful custom exceptions.   |
| **Repository Layer** | Manages in-memory collections, performs CRUD operations, and delegates persistence to storage classes.                    |
| **Storage Layer**    | Reads from and writes to files, converts file records into domain objects, and manages all persistence details.           |
| **Model Layer**      | Represents core domain objects and encapsulates business data and identity.                                               |

---

# Package Structure

**Base Package**

```text
com.librarymanagement
```

| Package        | Responsibility                                                                                                                 |
| -------------- | ------------------------------------------------------------------------------------------------------------------------------ |
| `model`        | Contains domain entities and enums such as `Book`, `Student`, `User`, `Librarian`, `BorrowRecord`, `Role`, and `BorrowStatus`. |
| `repository`   | Contains repository contracts that provide access to domain data while hiding storage details from services.                   |
| `service`      | Contains service interfaces that define business operations.                                                                   |
| `service.impl` | Contains concrete service implementations that apply business rules and coordinate repositories.                               |
| `storage`      | Contains file handling classes responsible for loading and saving application data.                                            |
| `menu`         | Contains console UI classes including the main, librarian, and student menus.                                                  |
| `exception`    | Contains custom exceptions representing business and validation failures.                                                      |
| `util`         | Contains reusable helper classes for validation, date handling, fine calculation, and ID generation.                           |
| `config`       | Contains configurable application settings such as file paths, borrow limits, loan periods, and fine amounts.                  |
| `constant`     | Contains fixed application constants such as file names, delimiters, and common messages.                                      |

---

# Folder Structure

```text
LibraryManagementSystem
│
├── data
│   ├── books.txt
│   ├── students.txt
│   ├── borrow_records.txt
│   └── users.txt
│
├── docs
│   ├── architecture
│   │   └── Architecture.md
│   │
│   ├── domain
│   │   └── DomainModel.md
│   │
│   ├── decisions
│   │   └── ArchitectureDecisions.md
│   │
│   └── diagrams
│       └── ClassDiagram.md
│
├── src
│   └── main
│       └── java
│           └── com
│               └── librarymanagement
│                   ├── Main
│                   ├── config
│                   ├── constant
│                   ├── exception
│                   ├── menu
│                   ├── model
│                   ├── repository
│                   ├── service
│                   │   └── impl
│                   ├── storage
│                   └── util
│
└── README.md
```

---

# Naming Conventions

Consistent naming conventions improve readability and maintainability.

| Element    | Convention                 | Example                              |
| ---------- | -------------------------- | ------------------------------------ |
| Classes    | PascalCase                 | `Book`, `BorrowRecord`               |
| Interfaces | PascalCase                 | `BookService`, `StudentRepository`   |
| Variables  | camelCase                  | `bookId`, `availableCopies`          |
| Methods    | camelCase (verb-based)     | `addBook`, `calculateFine`           |
| Constants  | UPPER_SNAKE_CASE           | `MAX_BORROW_LIMIT`                   |
| Packages   | lowercase                  | `com.librarymanagement.service.impl` |
| Files      | Match the public type name | `BookService.java`                   |

### Additional Guidelines

* Interfaces should **not** use the `I` prefix.
* Class and method names should be meaningful.
* Avoid unnecessary abbreviations.

---

# Coding Standards

The following coding standards should be followed throughout the project.

| Standard           | Rule                                                                                             |
| ------------------ | ------------------------------------------------------------------------------------------------ |
| Encapsulation      | Use private fields with controlled access. Avoid public mutable fields.                          |
| Visibility         | Use the most restrictive visibility possible.                                                    |
| Method Size        | Keep methods focused, generally around **10–30 lines**.                                          |
| Class Size         | Each class should have one clearly defined responsibility.                                       |
| Exception Handling | Use custom exceptions for business failures and handle them gracefully at menu boundaries.       |
| Documentation      | Every class should document its purpose, responsibilities, relationships, and important methods. |
| Validation         | Separate input validation from business validation.                                              |
| Duplication        | Move repeated logic into utilities or shared helpers when the pattern becomes clear.             |

---

# Design Principles

The project follows established software engineering principles while avoiding unnecessary complexity.

| Principle                       | Application in This Project                                                                            |
| ------------------------------- | ------------------------------------------------------------------------------------------------------ |
| Separation of Concerns          | Menu, service, repository, storage, and model responsibilities remain independent.                     |
| High Cohesion                   | Each class contains only closely related behavior.                                                     |
| Low Coupling                    | Higher layers depend on abstractions where appropriate.                                                |
| Single Responsibility Principle | Every class has one clear reason to change.                                                            |
| Open/Closed Principle           | Storage implementations can change without modifying business logic.                                   |
| Liskov Substitution Principle   | If user inheritance is introduced, subclasses must safely replace the base user type.                  |
| Interface Segregation Principle | Service interfaces remain focused and avoid unnecessary methods.                                       |
| Dependency Inversion Principle  | Menu and service layers depend on contracts rather than concrete implementations whenever appropriate. |
| Clean Code                      | Prefer meaningful names, small methods, simple control flow, and predictable structure.                |

> The project should **not** be overengineered. SOLID principles should be applied only where they improve clarity and maintainability.

---

# High-Level Data Flow

### Example: Student Borrows a Book

```text
Student
   │
   ▼
StudentMenu
   │
   ▼
BorrowService
   │
   ▼
BookRepository + StudentRepository + BorrowRecordRepository
   │
   ▼
BookFileStorage + BorrowRecordFileStorage
   │
   ▼
books.txt + borrow_records.txt
```

---

## Flow Description

1. The student selects **Borrow Book** from the Student Menu.
2. The menu collects the required input.
3. The menu invokes the Borrow Service.
4. The service validates:

   * Student existence
   * Book existence
   * Book availability
   * Borrow limit
   * Duplicate active borrow rules
5. The service creates a borrow record and updates the available book copies.
6. The repositories update their in-memory collections.
7. The storage layer persists the updated data to the text files.
8. The menu displays either a success message or an appropriate error message.

> **Business rules always belong to the Service Layer.**
> The Menu Layer is responsible only for user interaction.

---

# Future Scalability

The architecture has been designed so that future enhancements require minimal structural changes.

| Future Direction | Architectural Support                                                                                    |
| ---------------- | -------------------------------------------------------------------------------------------------------- |
| JDBC             | Repository interfaces remain unchanged while replacing file-based implementations.                       |
| PostgreSQL       | Domain entities naturally map to tables such as `books`, `students`, `users`, and `borrow_records`.      |
| REST APIs        | Console menus can later be replaced by controllers while services remain reusable.                       |
| Spring Boot      | Services and repositories already follow a structure compatible with Spring-managed beans.               |
| Node.js Backend  | The same route → service → repository structure can be recreated in Node.js.                             |
| React Frontend   | React can communicate with future REST APIs while business logic remains unchanged in the service layer. |

---

# Architecture Review Summary

## Strengths

* Clear layered architecture.
* Strong separation of concerns.
* Beginner-friendly while maintaining professional design.
* Easy to explain during interviews.
* Supports file handling today and database migration later.
* Uses Object-Oriented Programming without unnecessary inheritance.
* Keeps business rules centralized within the service layer.
* Protects domain integrity through business validation.
* Uses enums and custom exceptions appropriately.

---

## Potential Risks

* The number of classes may initially feel overwhelming for beginners.
* File parsing can become difficult if the storage format is not carefully designed.
* In-memory collections must remain synchronized with the persisted text files.
* Plain-text password storage is acceptable only for MVP learning purposes and should never be used in production.

---

## Assumptions

* The MVP uses **text files** instead of a database.
* Only **two user roles** are required initially:

  * Librarian
  * Student
* The borrow limit is configurable and is recommended to be **3 active books**.
* Fines are calculated on a **per overdue day** basis.
* Reports are generated dynamically and are not persisted.
* Authentication is simple and file-based.

---

## Reserved for Future Extensions

* JDBC repository implementations
* PostgreSQL schema
* Password hashing
* REST controllers
* Spring Boot dependency injection
* React frontend
* Admin role
* Student suspension or status management
* Advanced search and filtering
* Pagination for large datasets
* Audit logging

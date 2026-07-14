# Library Management System - Class Diagram

## Purpose

This document contains the approved ASCII UML/class diagram for the Library Management System domain model and major architectural interfaces.

No implementation code is included in this document.

## Domain Model Diagram

```text
+------------------+
|      User        |
+------------------+
| userId           |
| username         |
| password         |
| role             |
| linkedEntityId   |
+------------------+
          |
          | has
          v
+------------------+
|      Role        |
+------------------+
| LIBRARIAN        |
| STUDENT          |
+------------------+


+------------------+             +------------------+
|    Librarian     |             |     Student      |
+------------------+             +------------------+
| librarianId      |             | studentId        |
| name             |             | name             |
| email            |             | department       |
| phone            |             | year             |
+------------------+             | phone            |
                                 | email            |
                                 +------------------+
                                          |
                                          | 1
                                          |
                                          | has
                                          |
                                          | 0..*
                                          v
+------------------+             +----------------------+
|      Book        |             |    BorrowRecord      |
+------------------+             +----------------------+
| bookId           | 1       0..*| borrowId             |
| title            |-------------| bookId               |
| author           |             | studentId            |
| isbn             |             | issueDate            |
| category         |             | dueDate              |
| publisher        |             | returnDate           |
| publicationYear  |             | fine                 |
| totalCopies      |             | borrowStatus         |
| availableCopies  |             +----------------------+
| shelfNumber      |                       ^
+------------------+                       |
                                           |
                                           |
                                    +---------------+
                                    | BorrowStatus  |
                                    +---------------+
                                    | ACTIVE        |
                                    | RETURNED      |
                                    | OVERDUE       |
                                    +---------------+
```

## Service Layer Interfaces

```text
+----------------------+       +-----------------------+
|     BookService      |       |    StudentService     |
+----------------------+       +-----------------------+

+----------------------+       +-----------------------+
|    BorrowService     |       |     ReportService     |
+----------------------+       +-----------------------+

+--------------------------+
|  AuthenticationService   |
+--------------------------+
```

## Repository Layer Interfaces

```text
+----------------------+       +-----------------------+
|   BookRepository     |       |   StudentRepository   |
+----------------------+       +-----------------------+

+---------------------------+   +--------------------+
| BorrowRecordRepository    |   |   UserRepository   |
+---------------------------+   +--------------------+
```

## Storage Layer

```text
+------------------+
|   FileStorage    |
+------------------+
          ^
          |
          |
+----------------------+   +-------------------------+
|  BookFileStorage     |   | StudentFileStorage      |
+----------------------+   +-------------------------+

+---------------------------+   +----------------------+
| BorrowRecordFileStorage   |   | UserFileStorage      |
+---------------------------+   +----------------------+
```

## Relationship Summary

| Source | Target | Multiplicity | Relationship |
| --- | --- | --- | --- |
| User | Role | 1 to 1 | Association |
| User | Student | 0..1 to 0..1 | Optional one-to-one |
| User | Librarian | 0..1 to 0..1 | Optional one-to-one |
| Student | BorrowRecord | 1 to 0..* | One-to-many |
| Book | BorrowRecord | 1 to 0..* | One-to-many |
| Student | Book | * to * | Many-to-many through BorrowRecord |
| BorrowRecord | BorrowStatus | 1 to 1 | Association |

## Notes

- BorrowRecord is the association entity between Student and Book.
- Book availability is derived from availableCopies.
- Reports are generated dynamically and are not persisted.
- User authentication is separate from Student and Librarian profile data.

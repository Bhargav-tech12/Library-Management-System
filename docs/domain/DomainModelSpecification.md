# Library Management System - Domain Model

## Purpose

This document defines the approved domain model for the Library Management System. It describes the core entities, relationships, inheritance approach, interfaces, enums, and business rules.

No implementation code is included in this document.

## Entity Catalogue

### Book

| Item | Description |
| --- | --- |
| Purpose | Represents a library book title and its inventory details. |
| Responsibilities | Store book metadata, track total copies, track available copies, provide identity, and support searching. |
| Important Attributes | bookId, title, author, isbn, category, publisher, publicationYear, totalCopies, availableCopies, shelfNumber |
| Relationships | One book can have many borrow records. Borrow records reference books. |

### Student

| Item | Description |
| --- | --- |
| Purpose | Represents a registered student who can borrow books. |
| Responsibilities | Store student profile, identify borrower, and support borrowing eligibility checks. |
| Important Attributes | studentId, name, department, year, phone, email |
| Relationships | One student can have many borrow records. Borrow records reference students. |

### User

| Item | Description |
| --- | --- |
| Purpose | Represents a login account. |
| Responsibilities | Store authentication identity, username, password information, role, and link to a domain profile. |
| Important Attributes | userId, username, password, role, linkedEntityId |
| Relationships | One user has one role. A student user may be linked to one student. A librarian user may be linked to one librarian profile. |

### Librarian

| Item | Description |
| --- | --- |
| Purpose | Represents a librarian profile. |
| Responsibilities | Identify librarian users and support librarian-specific operations through role-based access. |
| Important Attributes | librarianId, name, email, phone |
| Relationships | A librarian may have one user account. A librarian performs book, student, and report operations through services. |

For the MVP, librarian data can remain simple. The user account is more important than librarian profile details.

### BorrowRecord

| Item | Description |
| --- | --- |
| Purpose | Represents a borrowing transaction between a student and a book. |
| Responsibilities | Store issue date, due date, return date, fine, and borrowing status. |
| Important Attributes | borrowId, bookId, studentId, issueDate, dueDate, returnDate, fine, borrowStatus |
| Relationships | Many borrow records belong to one student. Many borrow records refer to one book. BorrowRecord acts as an association entity between Student and Book. |

### Report

| Item | Description |
| --- | --- |
| Purpose | Represents calculated library statistics. |
| Responsibilities | Show total books, available books, borrowed books, overdue books, and total students. |
| Important Attributes | totalBooks, availableBooks, borrowedBooks, overdueBooks, totalStudents |
| Relationships | Derived from books, students, and borrow records. Does not own data. |

Reports are not persisted in the MVP. They are generated dynamically from current in-memory data.

## Relationships

| Relationship | Multiplicity | Type | Explanation |
| --- | --- | --- | --- |
| Student to BorrowRecord | 1 to 0..* | One-to-many | One student can borrow many books over time. Each borrow record belongs to one student. |
| Book to BorrowRecord | 1 to 0..* | One-to-many | One book can appear in many borrow records over time. Each borrow record refers to one book. |
| Student to Book | * to * | Many-to-many through BorrowRecord | A student can borrow many books, and a book can be borrowed by many students over time. |
| User to Role | 1 to 1 | Association | Each user has exactly one role. |
| User to Student | 0..1 to 0..1 | Optional one-to-one | A student login account may be linked to one student profile. |
| User to Librarian | 0..1 to 0..1 | Optional one-to-one | A librarian login account may be linked to one librarian profile. |

### Composition and Aggregation

| Concept | Decision |
| --- | --- |
| Composition | Not strongly required between major entities in the MVP. Books and students exist independently of borrow records. |
| Aggregation | BorrowRecord aggregates references to Book and Student through IDs. It depends conceptually on both but does not own their lifecycle. |

## Inheritance

The approved design avoids forcing inheritance where it does not improve clarity.

### Recommended MVP Approach

Use:

```text
User
Role enum
Student
Librarian
```

Student and Librarian remain separate domain profiles. User represents authentication. Role controls access.

### Why This Approach

A student profile is not the same as a user account:

- Student contains academic and contact information.
- User contains login and role information.

Keeping them separate prevents responsibility mixing.

### Possible Future Inheritance

If polymorphic login behavior becomes useful, a controlled user hierarchy may be introduced:

```text
AbstractUser
 |
 |-- StudentUser
 |-- LibrarianUser
```

This is not required for the MVP unless implementation remains simple.

### Interview Explanation

We avoided unnecessary inheritance between Student and User because a student is a library member, while a user is an authentication account. They are related, but they do not represent the same responsibility.

## Interfaces

| Interface | Purpose | Reason |
| --- | --- | --- |
| BookService | Defines book-related business operations. | Separates menu layer from implementation and supports future controller reuse. |
| StudentService | Defines student management operations. | Keeps student business logic separate from menu code. |
| BorrowService | Defines borrowing and returning operations. | Borrowing involves multiple entities and business rules. |
| ReportService | Defines report-generation operations. | Reports are derived from multiple repositories and should not be mixed into menu code. |
| AuthenticationService | Defines login and user validation operations. | Authentication is separate from book, student, and borrow logic. |
| BookRepository | Defines access operations for books. | Allows file-based persistence now and database persistence later. |
| StudentRepository | Defines access operations for students. | Keeps student data access independent from services. |
| BorrowRecordRepository | Defines access operations for borrow records. | Borrow history and active borrow tracking need focused data access. |
| UserRepository | Defines access operations for login users. | Authentication should not directly read files. |
| FileStorage | Defines file load/save behavior. | Makes file persistence reusable and testable. |

## Enumerations

| Enum | Values | Reason |
| --- | --- | --- |
| Role | LIBRARIAN, STUDENT | Avoids string comparison bugs and invalid role values. |
| BorrowStatus | ACTIVE, RETURNED, OVERDUE | Keeps borrowing state controlled and predictable. |
| BookStatus | AVAILABLE, UNAVAILABLE | Can represent availability, but for MVP availability should be derived from availableCopies instead of stored. |
| SearchType | BY_ID, BY_TITLE, BY_AUTHOR, BY_ISBN, BY_CATEGORY | Supports menu-driven search logic without unclear search mode strings. |

Enums are preferred over strings because they are type-safe, easier to validate, and prevent inconsistent values.

## Domain Rules

| Rule | Description |
| --- | --- |
| Borrow limit | A student can borrow only a fixed maximum number of active books. Recommended MVP value is 3 active books per student. |
| Book availability | A book can be borrowed only if availableCopies is greater than 0. |
| Borrow copy update | Borrowing decreases availableCopies by 1. Returning increases availableCopies by 1. |
| Copy integrity | availableCopies must never be negative or greater than totalCopies. |
| Fine calculation | Fine applies only after due date and is calculated as overdue days multiplied by fine per day. |
| Fine value | Recommended configurable value is 5 currency units per overdue day. |
| Duplicate ISBN | ISBN should be unique for a book title. Existing ISBN should increase copies instead of creating a duplicate book record. |
| Student eligibility | Student must exist, stay within borrow limit, not already have the same active book, and borrow only available books. |
| Book deletion | A book should not be deleted if it has active borrow records. |
| Student deletion | A student should not be deleted if they have active borrowed books. |
| Return book | A book can be returned only when an active matching borrow record exists. |
| Reports | Reports are generated from current in-memory collections and are not persisted. |

## Domain Design Decisions

| Decision | Approved Approach |
| --- | --- |
| BorrowRecord as association entity | BorrowRecord connects Student and Book because borrowing has its own data: issue date, due date, return date, fine, and status. |
| User separated from Student and Librarian | Authentication data and domain profile data remain separate. |
| Role as enum | Role is represented by an enum instead of strings. |
| Availability derived from copies | Book availability is derived from availableCopies instead of stored separately. |
| Reports generated dynamically | Reports are derived views and are not persisted. |

## Domain Review Summary

### Strengths

- The model maps clearly to real library concepts.
- BorrowRecord correctly handles the many-to-many relationship between students and books.
- Authentication is separated from student and librarian profiles.
- Business rules protect data integrity.
- Enums reduce invalid values and string comparison errors.

### Risks

- File-based persistence must preserve relationships using stable IDs.
- Duplicate ISBN handling must be implemented consistently.
- Fine calculation depends on reliable date handling.

### Reserved Extensions

- Student account status or suspension.
- Librarian profile expansion.
- Advanced borrowing policies by student year or department.
- Book reservations.
- Audit history.

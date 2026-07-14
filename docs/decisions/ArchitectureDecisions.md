# Library Management System - Architecture Decisions

## Purpose

This document records the approved architecture and domain design decisions for the Library Management System. These decisions should guide future implementation and review.

No implementation code is included in this document.

## Decision 1: Use Layered Architecture

| Item | Description |
| --- | --- |
| Chosen Approach | Use menu, service, repository, storage, and model layers. |
| Why | This structure is simple, professional, maintainable, and easy to explain. |
| Alternative | Put all logic directly inside menu classes. |
| Trade-off | Layered architecture requires more files, but greatly improves separation of concerns and maintainability. |
| Interview Explanation | The presentation, business logic, data access, and file storage responsibilities are separated so each class has a clear reason to change. |

## Decision 2: Hide File Storage Behind Repository Layer

| Item | Description |
| --- | --- |
| Chosen Approach | Repositories manage in-memory collections and delegate file operations to storage classes. |
| Why | The service layer should not care whether data comes from text files or a database. |
| Alternative | Services directly read and write files. |
| Trade-off | This adds structure, but keeps future persistence migration cleaner. |
| Interview Explanation | Repositories abstract data access. Today they can use files; later they can use JDBC without rewriting business logic. |

## Decision 3: Model BorrowRecord as an Association Entity

| Item | Description |
| --- | --- |
| Chosen Approach | Student and Book are connected through BorrowRecord. |
| Why | Borrowing has its own data: issue date, due date, return date, fine, and status. |
| Alternative | Store borrowed book IDs directly inside Student. |
| Trade-off | BorrowRecord is slightly more complex but more realistic and maintainable. |
| Interview Explanation | Since borrowing has attributes of its own, it is modeled as a separate entity instead of a simple list inside Student. |

## Decision 4: Use Role Enum Instead of Role Strings

| Item | Description |
| --- | --- |
| Chosen Approach | Use a Role enum. |
| Why | It prevents invalid role values and string comparison mistakes. |
| Alternative | Store roles as plain strings. |
| Trade-off | Enums are less flexible than database-driven roles, but they are appropriate for the MVP. |
| Interview Explanation | Enums are used for fixed domain values because they are type-safe and easier to validate. |

## Decision 5: Keep Authentication Separate from Domain Profiles

| Item | Description |
| --- | --- |
| Chosen Approach | Use User for login and keep Student and Librarian as profile/domain entities. |
| Why | Authentication and domain profile data are different responsibilities. |
| Alternative | Put username and password directly inside Student and Librarian. |
| Trade-off | This adds a small amount of modeling effort, but keeps the design cleaner. |
| Interview Explanation | A user account is not the same as a student profile. Separating them keeps authentication independent from domain data. |

## Decision 6: Derive Book Availability

| Item | Description |
| --- | --- |
| Chosen Approach | Use availableCopies and derive whether a book is available. |
| Why | A separately stored status can become inconsistent with copy counts. |
| Alternative | Store BookStatus as persistent state. |
| Trade-off | Derived status requires a small calculation, but avoids duplicate state. |
| Interview Explanation | Redundant state is avoided. If available copies are greater than zero, the book is available. |

## Decision 7: Do Not Persist Reports

| Item | Description |
| --- | --- |
| Chosen Approach | Generate reports from current books, students, and borrow records. |
| Why | Reports are summaries, not primary business records. |
| Alternative | Store report data in a file. |
| Trade-off | Reports are recalculated each time, but they stay accurate and avoid duplicate data. |
| Interview Explanation | Reports are derived views. Persisting them would introduce duplicate data and possible inconsistency. |

## Decision 8: Avoid Unnecessary Inheritance

| Item | Description |
| --- | --- |
| Chosen Approach | Keep User, Student, and Librarian separate for the MVP, using Role for access control. |
| Why | Student and Librarian profiles are not the same responsibility as authentication accounts. |
| Alternative | Make Student and Librarian inherit directly from User. |
| Trade-off | The design uses composition/association instead of demonstrating inheritance everywhere, but it is cleaner and more realistic. |
| Interview Explanation | Inheritance is used only where it models a true is-a relationship. Student is not simply a User; it is a domain profile that may have a user account. |

## Decision 9: Use Custom Exceptions for Business Failures

| Item | Description |
| --- | --- |
| Chosen Approach | Use meaningful custom exceptions such as BookNotFoundException, StudentNotFoundException, BorrowLimitExceededException, BookUnavailableException, and InvalidInputException. |
| Why | Custom exceptions make failures clearer and easier to handle gracefully. |
| Alternative | Use generic exceptions or return status strings. |
| Trade-off | More classes are required, but error handling becomes more professional and readable. |
| Interview Explanation | Custom exceptions communicate domain-specific failure reasons and keep business error handling explicit. |

## Decision 10: Generate Reports from Services

| Item | Description |
| --- | --- |
| Chosen Approach | Use a ReportService to calculate totals and overdue information from repositories. |
| Why | Report generation depends on multiple data sources and should not be placed in the menu layer. |
| Alternative | Calculate reports directly inside menu classes. |
| Trade-off | Adds a service interface and implementation, but keeps reporting logic reusable. |
| Interview Explanation | ReportService centralizes reporting rules and keeps the presentation layer focused only on display. |

## Approved Assumptions

| Assumption | Value |
| --- | --- |
| Storage | Text files |
| Roles | Librarian and Student |
| Borrow limit | Configurable, recommended as 3 active books |
| Fine rule | Overdue days multiplied by fine per day |
| Fine amount | Configurable, recommended as 5 currency units per overdue day |
| Reports | Generated dynamically and not persisted |
| Authentication | Simple file-based login for MVP |

## Future Extension Boundaries

The approved architecture reserves room for:

- JDBC repository implementations.
- PostgreSQL persistence.
- Password hashing.
- REST controllers.
- Spring Boot dependency injection.
- React frontend.
- Admin role.
- Student suspension or status.
- Advanced search and filtering.
- Pagination for large datasets.
- Audit logs.

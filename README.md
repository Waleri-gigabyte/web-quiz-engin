# Web Quiz Engine

A RESTful backend application for creating, managing, and solving quizzes, built with Java and Spring Boot.

The project demonstrates backend development concepts, including REST API design, user authentication, authorization, database persistence, pagination, and layered architecture.

## Features

- User registration and authentication
- Secure password storage
- Quiz creation and retrieval
- Multiple-choice questions with multiple correct answers
- Automatic answer validation
- Quiz deletion restricted to its owner
- Persistent quiz completion history
- Support for multiple completions of the same quiz
- Pagination with 10 elements per page
- Sorting completion history by completion time
- Request validation and exception handling

## Technology Stack

| Technology | Purpose |
|---|---|
| Java | Core programming language |
| Spring Boot | Application framework |
| Spring Web | REST API development |
| Spring Security | Authentication and authorization |
| Spring Data JPA | Database access |
| Hibernate | Object-relational mapping |
| Jakarta Validation | Request validation |
| Gradle | Build and dependency management |
| Git | Version control |

## Application Architecture

The application follows a layered architecture with separation of responsibilities:

- **Controller layer** — handles HTTP requests and responses.
- **Service layer** — implements business logic.
- **Repository layer** — manages database operations.
- **Entity layer** — represents persistent data.
- **DTO layer** — defines request and response objects.
- **Security layer** — manages authentication and access control.

## REST API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/register` | Register a new user |
| POST | `/api/quizzes` | Create a new quiz |
| GET | `/api/quizzes/{id}` | Retrieve a quiz by ID |
| GET | `/api/quizzes?page=0` | Retrieve paginated quizzes |
| POST | `/api/quizzes/{id}/solve` | Submit an answer |
| DELETE | `/api/quizzes/{id}` | Delete a quiz owned by the authenticated user |
| GET | `/api/quizzes/completed?page=0` | Retrieve personal quiz completion history |

## Security

The application implements authentication and authorization using Spring Security.

Authenticated users can create and solve quizzes, access their personal completion history, and delete only the quizzes they own.

## Quiz Completion History

Every successful quiz completion is stored as a separate database record containing:

- User ID
- Quiz ID
- Completion timestamp

This approach allows users to complete the same quiz multiple times while preserving the history of successful attempts.

## Pagination

Pagination is implemented using Spring Data's `Page` and `PageRequest` interfaces.

Both the quiz list and personal completion history support paginated responses, reducing unnecessary data retrieval.

## Key Concepts Practiced

- RESTful API design
- Dependency injection
- Layered application architecture
- Authentication and authorization
- Entity-to-DTO mapping
- Database persistence
- Spring Data repository queries
- Pagination and sorting
- Java Stream API and lambda expressions
- Exception handling
- Data validation

## Future Improvements

- JWT-based authentication
- Swagger / OpenAPI documentation
- Docker containerization
- Extended unit and integration testing
- CI/CD pipeline using GitHub Actions
- Standalone deployment configuration

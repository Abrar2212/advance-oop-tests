# Student Management Backend

A RESTful API built with Spring Boot for managing student information.

## 🏗️ Architecture

### Technology Stack
- **Java 17**
- **Spring Boot 3.2.1**
- **Spring Data JPA** - Data persistence
- **H2 Database** - In-memory database
- **Lombok** - Reduce boilerplate code
- **Maven** - Build and dependency management

### Project Structure

```
backend/
├── src/main/java/com/studentmanagement/
│   ├── StudentManagementApplication.java    # Main application class
│   ├── config/
│   │   ├── CorsConfig.java                  # CORS configuration
│   │   └── DataInitializer.java             # Sample data setup
│   ├── controller/
│   │   └── StudentController.java           # REST API endpoints
│   ├── exception/
│   │   ├── DuplicateResourceException.java  # Custom exception
│   │   ├── ResourceNotFoundException.java   # Custom exception
│   │   └── GlobalExceptionHandler.java      # Global error handling
│   ├── model/
│   │   └── Student.java                     # Student entity
│   ├── repository/
│   │   └── StudentRepository.java           # Data access layer
│   └── service/
│       ├── StudentService.java              # Service interface
│       └── StudentServiceImpl.java          # Service implementation
├── src/main/resources/
│   └── application.properties               # Application configuration
└── pom.xml                                  # Maven dependencies
```

## 🔌 API Endpoints

### Base URL: `http://localhost:8080/api/students`

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/students` | Get all students |
| GET | `/api/students/{id}` | Get student by ID |
| POST | `/api/students` | Create new student |
| PUT | `/api/students/{id}` | Update entire student |
| PATCH | `/api/students/{id}` | Partially update student |
| DELETE | `/api/students/{id}` | Delete student |
| GET | `/api/students/health` | Health check |

### Sample Request/Response

#### Create Student (POST)
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "dateOfBirth": "2002-05-15",
  "phoneNumber": "1234567890",
  "address": "123 Main St",
  "major": "Computer Science",
  "gpa": 3.8,
  "enrollmentStatus": "ACTIVE"
}
```

#### Response (201 Created)
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "dateOfBirth": "2002-05-15",
  "phoneNumber": "1234567890",
  "address": "123 Main St",
  "major": "Computer Science",
  "gpa": 3.8,
  "enrollmentStatus": "ACTIVE"
}
```

#### Partial Update (PATCH)
```json
{
  "gpa": 3.9,
  "enrollmentStatus": "GRADUATED"
}
```

## 🎯 Object-Oriented Programming Principles

### 1. Encapsulation
- Private fields in `Student` entity with getters/setters
- Service layer encapsulates business logic

### 2. Abstraction
- `StudentService` interface abstracts implementation details
- Repository interface abstracts data access

### 3. Inheritance
- Custom exceptions extend `RuntimeException`
- Entity classes can be extended for specialized types

### 4. Polymorphism
- Service interface allows multiple implementations
- Exception handling uses polymorphic behavior

### 5. SOLID Principles
- **Single Responsibility**: Each class has one clear purpose
- **Open/Closed**: Extensible through interfaces
- **Liskov Substitution**: Service implementations are interchangeable
- **Interface Segregation**: Focused interfaces
- **Dependency Inversion**: Depends on abstractions, not concrete classes

## 🚀 Running the Application

### Using Maven
```bash
cd backend
./mvnw clean install
./mvnw spring-boot:run
```

### Using Java
```bash
cd backend
./mvnw clean package
java -jar target/student-management-backend-1.0.0.jar
```

The server will start on `http://localhost:8080`

## 🗄️ Database Access

### H2 Console
Access the H2 database console at: `http://localhost:8080/h2-console`

**Connection Details:**
- JDBC URL: `jdbc:h2:mem:studentdb`
- Username: `sa`
- Password: _(leave empty)_

## 🧪 Testing the API

### Using curl

```bash
# Get all students
curl http://localhost:8080/api/students

# Get student by ID
curl http://localhost:8080/api/students/1

# Create student
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{"firstName":"John","lastName":"Doe","email":"john@test.com","dateOfBirth":"2002-05-15","major":"CS","gpa":3.8,"enrollmentStatus":"ACTIVE"}'

# Update student
curl -X PUT http://localhost:8080/api/students/1 \
  -H "Content-Type: application/json" \
  -d '{"firstName":"John","lastName":"Doe","email":"john@test.com","dateOfBirth":"2002-05-15","major":"CS","gpa":3.9,"enrollmentStatus":"ACTIVE"}'

# Partial update
curl -X PATCH http://localhost:8080/api/students/1 \
  -H "Content-Type: application/json" \
  -d '{"gpa":4.0}'

# Delete student
curl -X DELETE http://localhost:8080/api/students/1
```

## 📝 Validation Rules

- **firstName**: Required, 2-50 characters
- **lastName**: Required, 2-50 characters
- **email**: Required, valid email format, unique
- **dateOfBirth**: Required, must be in the past
- **phoneNumber**: Optional, 10-15 digits
- **address**: Optional, max 200 characters
- **major**: Required, 2-100 characters
- **gpa**: Optional, 0.0-4.0
- **enrollmentStatus**: Required, enum (ACTIVE, INACTIVE, GRADUATED, SUSPENDED)

## 🔧 Configuration

Key configuration in `application.properties`:
- Server port: 8080
- Database: H2 in-memory
- JPA: Auto DDL generation
- CORS: Enabled for localhost:3000 and localhost:5173

## 📚 Dependencies

- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Validation
- H2 Database
- Lombok
- Spring Boot DevTools

## 🎓 Learning Outcomes

This backend demonstrates:
1. RESTful API design and implementation
2. Spring Boot application structure
3. JPA entity mapping and relationships
4. Service layer pattern
5. Repository pattern
6. Exception handling and validation
7. CORS configuration for frontend integration
8. Object-oriented programming principles
9. Clean code and documentation practices

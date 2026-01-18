# Student Management System

A full-stack CRUD application for managing student records with a React TypeScript frontend and Spring Boot backend.

## 📋 Project Overview

This Student Management System is a comprehensive web application that demonstrates:

- **Full CRUD Operations**: Create, Read, Update, and Delete student records
- **RESTful API Design**: Clean API architecture following REST principles
- **Modern Frontend**: React with TypeScript and Tailwind CSS
- **Robust Backend**: Spring Boot with JPA/Hibernate and H2 database
- **Form Validation**: Client-side and server-side validation
- **Responsive Design**: Mobile-friendly user interface
- **Error Handling**: Comprehensive error handling with user-friendly messages

### Features

- ✅ Add new students with personal and academic information
- ✅ View all students in a paginated table
- ✅ Edit existing student records
- ✅ Delete students with confirmation
- ✅ Form validation (email format, GPA range, phone number pattern, etc.)
- ✅ Enrollment status tracking (Active, Inactive, Graduated, Suspended)
- ✅ Real-time data synchronization between frontend and backend

---

## 🚀 Running the Project

### Prerequisites

- **Java 17** or higher
- **Node.js 18** or higher
- **npm** or **yarn**
- **Maven** (included via Maven wrapper)

### Option 1: Using GitHub Codespaces

1. **Open in Codespaces**
   - Navigate to the GitHub repository
   - Click the **Code** button
   - Select **Codespaces** tab
   - Click **Create codespace on main**

2. **Wait for Environment Setup**
   - Codespaces will automatically install dependencies
   - The dev container configuration will set up Java, Node.js, and Maven

3. **Start Backend Service**
   ```bash
   cd backend
   ./mvnw spring-boot:run
   ```
   - Backend will start on `http://localhost:8080`
   - H2 Console available at `http://localhost:8080/h2-console`

4. **Start Frontend Service** (in a new terminal)
   ```bash
   cd frontend
   npm install
   npm run dev
   ```
   - Frontend will start on `http://localhost:5173`

5. **Access the Application**
   - Codespaces will forward ports automatically
   - Click on the **Ports** tab to see forwarded URLs
   - Open the frontend URL in your browser

### Option 2: Local Development

#### Step 1: Clone the Repository

```bash
git clone <repository-url>
cd advance-oop-tests
```

#### Step 2: Start the Backend

```bash
cd backend
./mvnw spring-boot:run
```

**On Windows:**
```bash
cd backend
mvnw.cmd spring-boot:run
```

The backend will start on `http://localhost:8080`

**Verify Backend is Running:**
- Navigate to `http://localhost:8080/api/students/health`
- You should see: `{"status":"UP","message":"Student Management API is running",...}`

#### Step 3: Start the Frontend (in a new terminal)

```bash
cd frontend
npm install
npm run dev
```

The frontend will start on `http://localhost:5173`

#### Step 4: Access the Application

Open your browser and navigate to:
- **Frontend**: http://localhost:5173
- **Backend API**: http://localhost:8080/api/students
- **H2 Database Console**: http://localhost:8080/h2-console

**H2 Console Credentials:**
- **JDBC URL**: `jdbc:h2:mem:studentdb`
- **Username**: `sa`
- **Password**: *(leave empty)*

---

## 🏗️ Project Structure

```
advance-oop-tests/
├── backend/                    # Spring Boot Backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/studentmanagement/
│   │   │   │   ├── config/           # Configuration classes
│   │   │   │   ├── controller/       # REST Controllers
│   │   │   │   ├── exception/        # Custom exceptions & handlers
│   │   │   │   ├── model/            # JPA Entities
│   │   │   │   ├── repository/       # JPA Repositories
│   │   │   │   └── service/          # Business logic services
│   │   │   └── resources/
│   │   │       └── application.properties
│   ├── pom.xml                # Maven dependencies
│   └── mvnw / mvnw.cmd        # Maven wrapper
│
├── frontend/                   # React TypeScript Frontend
│   ├── src/
│   │   ├── components/        # React components
│   │   │   ├── StudentForm.tsx
│   │   │   └── StudentList.tsx
│   │   ├── services/          # API service layer
│   │   ├── types/             # TypeScript type definitions
│   │   ├── App.tsx            # Main application component
│   │   └── main.tsx           # Entry point
│   ├── package.json           # npm dependencies
│   └── vite.config.ts         # Vite configuration
│
└── README.md                   # This file
```

---

## 🔧 Technology Stack

### Backend
- **Framework**: Spring Boot 3.2.1
- **Language**: Java 17
- **Database**: H2 (in-memory)
- **ORM**: Hibernate/JPA
- **Build Tool**: Maven
- **Validation**: Jakarta Validation (Bean Validation)
- **Utilities**: Lombok

### Frontend
- **Framework**: React 19
- **Language**: TypeScript 5.9
- **Styling**: Tailwind CSS 3.4
- **HTTP Client**: Axios 1.6
- **Build Tool**: Vite 7.2
- **Linting**: ESLint

---

## 📡 API Endpoints

### Base URL: `http://localhost:8080/api/students`

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | Get all students |
| GET | `/{id}` | Get student by ID |
| POST | `/` | Create new student |
| PUT | `/{id}` | Update student (full update) |
| PATCH | `/{id}` | Partial update student |
| DELETE | `/{id}` | Delete student |
| GET | `/health` | Health check endpoint |

### Sample Request (Create Student)

```bash
POST http://localhost:8080/api/students
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "dateOfBirth": "2002-05-15",
  "phoneNumber": "1234567890",
  "address": "123 Main St, New York, NY",
  "major": "Computer Science",
  "gpa": 3.8,
  "enrollmentStatus": "ACTIVE"
}
```

---

## 🎨 Design Decisions & Assumptions

### Backend Design Decisions

1. **H2 In-Memory Database**
   - Used for development and testing
   - Data is reset on application restart
   - Easy to switch to PostgreSQL/MySQL for production

2. **Repository Pattern**
   - Clean separation between data access and business logic
   - Easy to test and maintain

3. **Global Exception Handling**
   - Centralized error handling with `@RestControllerAdvice`
   - Consistent error response format
   - Field-level validation errors returned to frontend

4. **Data Initialization**
   - Sample data loaded on startup via `DataInitializer`
   - Useful for testing and demonstration

5. **CORS Configuration**
   - Enabled for `localhost:3000` and `localhost:5173`
   - Ready for frontend development

### Frontend Design Decisions

1. **TypeScript**
   - Type safety throughout the application
   - Better IDE support and refactoring
   - Separate type definitions in `types/` folder

2. **Component Architecture**
   - Reusable components (`StudentForm`, `StudentList`)
   - Props-based communication
   - State management at App level

3. **Form Validation**
   - Client-side validation for better UX
   - Data sanitization before submission
   - Empty optional fields excluded from requests

4. **Error Handling**
   - Detailed error messages from backend validation
   - User-friendly error display
   - Console logging for debugging

5. **API Service Layer**
   - Centralized API calls in `studentService.ts`
   - Axios configuration with base URL
   - Type-safe request/response handling

### Assumptions

1. **Authentication Not Required**
   - No login/authentication implemented
   - All endpoints are publicly accessible

2. **Single User Environment**
   - No multi-tenancy or user isolation

3. **Data Persistence**
   - H2 in-memory database means data is lost on restart
   - Switch to persistent database for production

4. **Email Uniqueness**
   - Student emails must be unique
   - Enforced at database and service layers

5. **Enrollment Status**
   - Four predefined statuses: ACTIVE, INACTIVE, GRADUATED, SUSPENDED
   - No custom status values allowed

6. **Phone Number Format**
   - Must be 10-15 digits
   - No special characters or formatting

7. **GPA Range**
   - Valid range: 0.0 to 4.0
   - Optional field (can be null)

---

## 🐛 Troubleshooting

### Backend Issues

**Problem**: Port 8080 already in use
```bash
# Solution: Kill the process using port 8080
# Windows:
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Linux/Mac:
lsof -ti:8080 | xargs kill -9
```

**Problem**: Maven build fails
```bash
# Solution: Clean and rebuild
./mvnw clean install
```

### Frontend Issues

**Problem**: Port 5173 already in use
```bash
# Solution: Vite will automatically try the next available port
# Or manually specify a different port in vite.config.ts
```

**Problem**: CORS errors
```bash
# Solution: Ensure backend CORS is configured for your frontend URL
# Check application.properties or CorsConfig.java
```

**Problem**: `npm install` fails
```bash
# Solution: Clear npm cache and reinstall
npm cache clean --force
rm -rf node_modules package-lock.json
npm install
```

---

## 📝 Future Enhancements

- [ ] User authentication and authorization
- [ ] Pagination for large student lists
- [ ] Advanced search and filtering
- [ ] Export/Import student data (CSV, Excel)
- [ ] Student profile images
- [ ] Course enrollment tracking
- [ ] Grade management
- [ ] Dark mode support
- [ ] Unit and integration tests
- [ ] Docker containerization

---

## 👨‍💻 Development Notes

### Running Tests

**Backend:**
```bash
cd backend
./mvnw test
```

**Frontend:**
```bash
cd frontend
npm test
```

### Building for Production

**Backend:**
```bash
cd backend
./mvnw clean package
java -jar target/student-management-backend-1.0.0.jar
```

**Frontend:**
```bash
cd frontend
npm run build
# Output in dist/ folder
```

---

## 📄 License

This project is for educational purposes.

---

## 🤝 Contributing

This is a lab assignment project. Contributions are not currently accepted.

---

## 📧 Contact

For questions or issues, please contact the development team.

package com.studentmanagement.controller;

import com.studentmanagement.model.Student;
import com.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST Controller for Student operations.
 * This class handles HTTP requests and defines all API endpoints.
 * 
 * Demonstrates RESTful API design principles:
 * - Resource-based URLs
 * - Standard HTTP methods (GET, POST, PUT, PATCH, DELETE)
 * - Appropriate HTTP status codes
 * - JSON request/response format
 * 
 * @RestController - Combines @Controller and @ResponseBody
 * @RequestMapping - Defines base URL path for all endpoints
 * @CrossOrigin - Enables CORS for frontend integration
 * @RequiredArgsConstructor - Lombok annotation for constructor injection
 */
@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    /**
     * GET /api/students
     * Retrieve all students.
     * 
     * @return List of all students with HTTP 200 OK
     */
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    /**
     * GET /api/students/{id}
     * Retrieve a single student by ID.
     * 
     * @param id The ID of the student to retrieve
     * @return The student with HTTP 200 OK, or 404 Not Found if not exists
     */
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    /**
     * POST /api/students
     * Create a new student.
     * 
     * @param student The student object from request body
     * @return The created student with HTTP 201 Created
     */
    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    /**
     * PUT /api/students/{id}
     * Update an existing student (full update).
     * All fields must be provided.
     * 
     * @param id The ID of the student to update
     * @param studentDetails The new student details from request body
     * @return The updated student with HTTP 200 OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody Student studentDetails) {
        Student updatedStudent = studentService.updateStudent(id, studentDetails);
        return ResponseEntity.ok(updatedStudent);
    }

    /**
     * PATCH /api/students/{id}
     * Partially update a student.
     * Only provided fields will be updated.
     * 
     * @param id The ID of the student to update
     * @param updates Map of field names and values to update
     * @return The updated student with HTTP 200 OK
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Student> partialUpdateStudent(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {
        Student updatedStudent = studentService.partialUpdateStudent(id, updates);
        return ResponseEntity.ok(updatedStudent);
    }

    /**
     * DELETE /api/students/{id}
     * Delete a student by ID.
     * 
     * @param id The ID of the student to delete
     * @return HTTP 204 No Content on successful deletion
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /api/students/health
     * Health check endpoint to verify the API is running.
     * 
     * @return Simple health check message
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "message", "Student Management API is running",
                "timestamp", java.time.LocalDateTime.now().toString()
        ));
    }
}

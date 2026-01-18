package com.studentmanagement.service;

import com.studentmanagement.model.Student;
import com.studentmanagement.exception.ResourceNotFoundException;
import com.studentmanagement.exception.DuplicateResourceException;

import java.util.List;
import java.util.Map;

/**
 * Service interface for Student operations.
 * This interface demonstrates the Service layer pattern and defines the contract
 * for business logic operations.
 * 
 * Benefits of using an interface:
 * - Abstraction: Hides implementation details
 * - Testability: Easy to create mock implementations for testing
 * - Flexibility: Can have multiple implementations
 */
public interface StudentService {

    /**
     * Retrieve all students from the database.
     * 
     * @return List of all students
     */
    List<Student> getAllStudents();

    /**
     * Retrieve a single student by their ID.
     * 
     * @param id The ID of the student to retrieve
     * @return The student with the given ID
     * @throws ResourceNotFoundException if student is not found
     */
    Student getStudentById(Long id);

    /**
     * Create a new student.
     * 
     * @param student The student object to create
     * @return The created student with generated ID
     * @throws DuplicateResourceException if email already exists
     */
    Student createStudent(Student student);

    /**
     * Update an existing student (full update).
     * 
     * @param id The ID of the student to update
     * @param studentDetails The new student details
     * @return The updated student
     * @throws ResourceNotFoundException if student is not found
     * @throws DuplicateResourceException if email conflicts with another student
     */
    Student updateStudent(Long id, Student studentDetails);

    /**
     * Partially update a student (only specified fields).
     * 
     * @param id The ID of the student to update
     * @param updates Map containing field names and their new values
     * @return The updated student
     * @throws ResourceNotFoundException if student is not found
     * @throws DuplicateResourceException if email conflicts with another student
     */
    Student partialUpdateStudent(Long id, Map<String, Object> updates);

    /**
     * Delete a student by their ID.
     * 
     * @param id The ID of the student to delete
     * @throws ResourceNotFoundException if student is not found
     */
    void deleteStudent(Long id);
}

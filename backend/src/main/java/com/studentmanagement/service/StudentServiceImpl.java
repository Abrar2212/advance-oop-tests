package com.studentmanagement.service;

import com.studentmanagement.model.Student;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.exception.ResourceNotFoundException;
import com.studentmanagement.exception.DuplicateResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Implementation of StudentService interface.
 * This class contains the business logic for student operations.
 * 
 * Demonstrates Object-Oriented Programming principles:
 * - Encapsulation: Private fields and methods
 * - Abstraction: Implements interface
 * - Single Responsibility: Only handles student business logic
 * 
 * @Service - Marks this class as a Spring service component
 * @Transactional - Ensures database operations are atomic
 * @RequiredArgsConstructor - Lombok annotation for constructor injection
 */
@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    /**
     * Retrieve all students from the database.
     * 
     * @return List of all students
     */
    @Override
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Retrieve a single student by their ID.
     * 
     * @param id The ID of the student to retrieve
     * @return The student with the given ID
     * @throws ResourceNotFoundException if student is not found
     */
    @Override
    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with id: " + id));
    }

    /**
     * Create a new student.
     * Validates that email doesn't already exist.
     * 
     * @param student The student object to create
     * @return The created student with generated ID
     * @throws DuplicateResourceException if email already exists
     */
    @Override
    public Student createStudent(Student student) {
        // Check if email already exists
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new DuplicateResourceException(
                    "Student with email " + student.getEmail() + " already exists");
        }
        
        return studentRepository.save(student);
    }

    /**
     * Update an existing student (full update).
     * All fields are replaced with new values.
     * 
     * @param id The ID of the student to update
     * @param studentDetails The new student details
     * @return The updated student
     * @throws ResourceNotFoundException if student is not found
     * @throws DuplicateResourceException if email conflicts with another student
     */
    @Override
    public Student updateStudent(Long id, Student studentDetails) {
        Student existingStudent = getStudentById(id);
        
        // Check if email is being changed and if it conflicts with another student
        if (!existingStudent.getEmail().equals(studentDetails.getEmail()) &&
            studentRepository.existsByEmail(studentDetails.getEmail())) {
            throw new DuplicateResourceException(
                    "Student with email " + studentDetails.getEmail() + " already exists");
        }
        
        // Update all fields
        existingStudent.setFirstName(studentDetails.getFirstName());
        existingStudent.setLastName(studentDetails.getLastName());
        existingStudent.setEmail(studentDetails.getEmail());
        existingStudent.setDateOfBirth(studentDetails.getDateOfBirth());
        existingStudent.setPhoneNumber(studentDetails.getPhoneNumber());
        existingStudent.setAddress(studentDetails.getAddress());
        existingStudent.setMajor(studentDetails.getMajor());
        existingStudent.setGpa(studentDetails.getGpa());
        existingStudent.setEnrollmentStatus(studentDetails.getEnrollmentStatus());
        
        return studentRepository.save(existingStudent);
    }

    /**
     * Partially update a student (only specified fields).
     * Only the fields present in the updates map are modified.
     * 
     * @param id The ID of the student to update
     * @param updates Map containing field names and their new values
     * @return The updated student
     * @throws ResourceNotFoundException if student is not found
     * @throws DuplicateResourceException if email conflicts with another student
     */
    @Override
    public Student partialUpdateStudent(Long id, Map<String, Object> updates) {
        Student existingStudent = getStudentById(id);
        
        // Apply each update
        updates.forEach((key, value) -> {
            switch (key) {
                case "firstName":
                    existingStudent.setFirstName((String) value);
                    break;
                case "lastName":
                    existingStudent.setLastName((String) value);
                    break;
                case "email":
                    String newEmail = (String) value;
                    if (!existingStudent.getEmail().equals(newEmail) &&
                        studentRepository.existsByEmail(newEmail)) {
                        throw new DuplicateResourceException(
                                "Student with email " + newEmail + " already exists");
                    }
                    existingStudent.setEmail(newEmail);
                    break;
                case "dateOfBirth":
                    existingStudent.setDateOfBirth(LocalDate.parse((String) value));
                    break;
                case "phoneNumber":
                    existingStudent.setPhoneNumber((String) value);
                    break;
                case "address":
                    existingStudent.setAddress((String) value);
                    break;
                case "major":
                    existingStudent.setMajor((String) value);
                    break;
                case "gpa":
                    existingStudent.setGpa(((Number) value).doubleValue());
                    break;
                case "enrollmentStatus":
                    existingStudent.setEnrollmentStatus(
                            Student.EnrollmentStatus.valueOf((String) value));
                    break;
                default:
                    // Ignore unknown fields
                    break;
            }
        });
        
        return studentRepository.save(existingStudent);
    }

    /**
     * Delete a student by their ID.
     * 
     * @param id The ID of the student to delete
     * @throws ResourceNotFoundException if student is not found
     */
    @Override
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }
}

package com.studentmanagement.repository;

import com.studentmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Student entity.
 * This interface demonstrates the Repository pattern and provides abstraction
 * over the data access layer.
 * 
 * JpaRepository provides built-in CRUD operations:
 * - save(): Create or update
 * - findById(): Read by ID
 * - findAll(): Read all
 * - deleteById(): Delete by ID
 * - count(): Count records
 * - existsById(): Check existence
 * 
 * Custom query methods can be added by following Spring Data JPA naming conventions.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Find a student by email address.
     * This method demonstrates derived query functionality in Spring Data JPA.
     * 
     * @param email The email address to search for
     * @return Optional containing the student if found, empty otherwise
     */
    Optional<Student> findByEmail(String email);

    /**
     * Check if a student exists with the given email.
     * Useful for validation before creating a new student.
     * 
     * @param email The email address to check
     * @return true if a student with this email exists, false otherwise
     */
    boolean existsByEmail(String email);
}

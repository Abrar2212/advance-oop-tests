package com.studentmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Student entity class representing a student in the system.
 * This class demonstrates Object-Oriented Programming principles:
 * - Encapsulation: Private fields with getters/setters via Lombok
 * - Abstraction: Represents a real-world student entity
 * 
 * JPA Annotations:
 * @Entity - Marks this class as a JPA entity
 * @Table - Specifies the table name in the database
 * @Id - Marks the primary key field
 * @GeneratedValue - Specifies how the primary key should be generated
 */
@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    /**
     * Unique identifier for the student.
     * Auto-generated using database identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * First name of the student.
     * Required field with length constraints.
     */
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    /**
     * Last name of the student.
     * Required field with length constraints.
     */
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    /**
     * Email address of the student.
     * Must be unique and follow email format.
     */
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    /**
     * Date of birth of the student.
     * Must be in the past.
     */
    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    /**
     * Phone number of the student.
     */
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone number must be between 10 and 15 digits")
    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    /**
     * Address of the student.
     */
    @Size(max = 200, message = "Address must not exceed 200 characters")
    @Column(name = "address", length = 200)
    private String address;

    /**
     * Student's major or field of study.
     */
    @NotBlank(message = "Major is required")
    @Size(min = 2, max = 100, message = "Major must be between 2 and 100 characters")
    @Column(name = "major", nullable = false, length = 100)
    private String major;

    /**
     * Grade Point Average of the student.
     * Value between 0.0 and 4.0.
     */
    @DecimalMin(value = "0.0", message = "GPA must be at least 0.0")
    @DecimalMax(value = "4.0", message = "GPA must not exceed 4.0")
    @Column(name = "gpa")
    private Double gpa;

    /**
     * Enrollment status of the student.
     * Enum: ACTIVE, INACTIVE, GRADUATED, SUSPENDED
     */
    @NotNull(message = "Enrollment status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "enrollment_status", nullable = false)
    private EnrollmentStatus enrollmentStatus = EnrollmentStatus.ACTIVE;

    /**
     * Enum representing possible enrollment statuses for a student.
     */
    public enum EnrollmentStatus {
        ACTIVE,
        INACTIVE,
        GRADUATED,
        SUSPENDED
    }
}

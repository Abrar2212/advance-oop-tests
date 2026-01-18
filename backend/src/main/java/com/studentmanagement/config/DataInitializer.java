package com.studentmanagement.config;

import com.studentmanagement.model.Student;
import com.studentmanagement.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Data initializer to populate the database with sample data.
 * This class runs automatically when the application starts and creates
 * some initial student records for testing purposes.
 * 
 * @Component - Marks this class as a Spring component
 * CommandLineRunner - Interface that indicates this bean should run when the application starts
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final StudentRepository studentRepository;

    /**
     * Initialize sample data when the application starts.
     * 
     * @param args Command line arguments
     */
    @Override
    public void run(String... args) {
        // Clear existing data
        studentRepository.deleteAll();
        
        // Create sample students
        Student student1 = new Student();
        student1.setFirstName("John");
        student1.setLastName("Doe");
        student1.setEmail("john.doe@example.com");
        student1.setDateOfBirth(LocalDate.of(2002, 5, 15));
        student1.setPhoneNumber("1234567890");
        student1.setAddress("123 Main St, New York, NY");
        student1.setMajor("Computer Science");
        student1.setGpa(3.8);
        student1.setEnrollmentStatus(Student.EnrollmentStatus.ACTIVE);

        Student student2 = new Student();
        student2.setFirstName("Jane");
        student2.setLastName("Smith");
        student2.setEmail("jane.smith@example.com");
        student2.setDateOfBirth(LocalDate.of(2001, 8, 22));
        student2.setPhoneNumber("9876543210");
        student2.setAddress("456 Oak Ave, Los Angeles, CA");
        student2.setMajor("Electrical Engineering");
        student2.setGpa(3.9);
        student2.setEnrollmentStatus(Student.EnrollmentStatus.ACTIVE);

        Student student3 = new Student();
        student3.setFirstName("Mike");
        student3.setLastName("Johnson");
        student3.setEmail("mike.johnson@example.com");
        student3.setDateOfBirth(LocalDate.of(2003, 2, 10));
        student3.setPhoneNumber("5551234567");
        student3.setAddress("789 Pine Rd, Chicago, IL");
        student3.setMajor("Business Administration");
        student3.setGpa(3.5);
        student3.setEnrollmentStatus(Student.EnrollmentStatus.ACTIVE);

        Student student4 = new Student();
        student4.setFirstName("Emily");
        student4.setLastName("Brown");
        student4.setEmail("emily.brown@example.com");
        student4.setDateOfBirth(LocalDate.of(2000, 11, 30));
        student4.setPhoneNumber("5559876543");
        student4.setAddress("321 Elm St, Houston, TX");
        student4.setMajor("Mechanical Engineering");
        student4.setGpa(3.7);
        student4.setEnrollmentStatus(Student.EnrollmentStatus.ACTIVE);

        Student student5 = new Student();
        student5.setFirstName("David");
        student5.setLastName("Wilson");
        student5.setEmail("david.wilson@example.com");
        student5.setDateOfBirth(LocalDate.of(1999, 7, 18));
        student5.setPhoneNumber("5554567890");
        student5.setAddress("654 Maple Dr, Phoenix, AZ");
        student5.setMajor("Mathematics");
        student5.setGpa(4.0);
        student5.setEnrollmentStatus(Student.EnrollmentStatus.GRADUATED);

        // Save students to database
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        studentRepository.save(student4);
        studentRepository.save(student5);

        System.out.println("\n========================================");
        System.out.println("Sample data initialized successfully!");
        System.out.println("Created 5 sample students");
        System.out.println("========================================\n");
    }
}

package com.studentmanagement.exception;

/**
 * Custom exception for duplicate resource scenarios.
 * This exception is thrown when attempting to create a resource that already exists
 * (e.g., Student with duplicate email).
 * 
 * Demonstrates exception handling and object-oriented programming principles:
 * - Inheritance: Extends RuntimeException
 * - Encapsulation: Wraps error details
 */
public class DuplicateResourceException extends RuntimeException {

    /**
     * Constructor with error message.
     * 
     * @param message Detailed error message
     */
    public DuplicateResourceException(String message) {
        super(message);
    }

    /**
     * Constructor with error message and cause.
     * 
     * @param message Detailed error message
     * @param cause The underlying cause of the exception
     */
    public DuplicateResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}

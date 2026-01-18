package com.studentmanagement.exception;

/**
 * Custom exception for resource not found scenarios.
 * This exception is thrown when a requested resource (e.g., Student) is not found in the database.
 * 
 * Demonstrates exception handling and object-oriented programming principles:
 * - Inheritance: Extends RuntimeException
 * - Encapsulation: Wraps error details
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructor with error message.
     * 
     * @param message Detailed error message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor with error message and cause.
     * 
     * @param message Detailed error message
     * @param cause The underlying cause of the exception
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

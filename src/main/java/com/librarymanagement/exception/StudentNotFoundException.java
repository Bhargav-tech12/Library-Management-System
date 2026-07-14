package com.librarymanagement.exception;

/**
 * Indicates that a requested student could not be found.
 */
public class StudentNotFoundException extends Exception {
    public StudentNotFoundException() {
    }

    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

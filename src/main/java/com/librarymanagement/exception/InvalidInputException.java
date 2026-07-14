package com.librarymanagement.exception;

/**
 * Indicates that supplied input is invalid for the requested operation.
 */
public class InvalidInputException extends Exception {
    public InvalidInputException() {
    }

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}

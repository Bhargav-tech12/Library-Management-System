package com.librarymanagement.exception;

/**
 * Indicates that a requested book could not be found.
 */
public class BookNotFoundException extends Exception {
    public BookNotFoundException() {
    }

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

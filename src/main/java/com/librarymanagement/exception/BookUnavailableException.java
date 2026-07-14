package com.librarymanagement.exception;

/**
 * Indicates that a requested book is not available for borrowing.
 */
public class BookUnavailableException extends Exception {
    public BookUnavailableException() {
    }

    public BookUnavailableException(String message) {
        super(message);
    }

    public BookUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}

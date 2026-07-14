package com.librarymanagement.exception;

/**
 * Indicates that a student has reached the allowed borrowing limit.
 */
public class BorrowLimitExceededException extends Exception {
    public BorrowLimitExceededException() {
    }

    public BorrowLimitExceededException(String message) {
        super(message);
    }

    public BorrowLimitExceededException(String message, Throwable cause) {
        super(message, cause);
    }
}

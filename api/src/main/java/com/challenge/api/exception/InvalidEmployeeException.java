package com.challenge.api.exception;

// Thrown when invalid employee data is in a request
public class InvalidEmployeeException extends RuntimeException {
    public InvalidEmployeeException(String message) {
        super(message);
    }
}

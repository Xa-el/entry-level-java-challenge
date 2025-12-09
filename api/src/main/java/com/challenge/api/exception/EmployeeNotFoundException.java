package com.challenge.api.exception;

// Thrown when the UUID of an employee cannot be found
public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}

package com.jrinehuls.employeemgmt.exception;

import org.springframework.http.HttpStatus;

public class EmployeeConflictException extends RuntimeException {

    public EmployeeConflictException(String message) {
        super(message + " is already in use.");
    }

    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }
}

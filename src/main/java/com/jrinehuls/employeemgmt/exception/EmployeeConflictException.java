package com.jrinehuls.employeemgmt.exception;

import org.springframework.http.HttpStatus;

public class EmployeeConflictException extends RuntimeException {

    private final String field;

    public EmployeeConflictException(String field, String value) {
        super(value + " is already in use.");
        this.field = field;
    }

    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }

    public String getField() {
        return this.field;
    }

}

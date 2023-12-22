package com.jrinehuls.employeemgmt.exception;

import org.springframework.http.HttpStatus;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(Long id) {
        super(String.format("Employee with id: %d not found", id));
    }

    public HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }
}

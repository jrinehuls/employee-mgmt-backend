package com.jrinehuls.employeemgmt;

import com.jrinehuls.employeemgmt.model.dto.error.ErrorResponse;
import com.jrinehuls.employeemgmt.exception.EmployeeConflictException;
import com.jrinehuls.employeemgmt.exception.EmployeeNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmployeeConflictException.class)
    protected ResponseEntity<ErrorResponse> handleEmployeeConflictException(EmployeeConflictException ex) {
        ArrayList<String> messages = new ArrayList<>();
        messages.add(ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(messages), ex.getStatusCode());
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        ArrayList<String> messages = new ArrayList<>();
        messages.add(ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(messages), ex.getStatusCode());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ArrayList<String> messages = new ArrayList<>();
        for (ObjectError error: ex.getBindingResult().getAllErrors()) {
            messages.add(error.getDefaultMessage());
        }
        return new ResponseEntity<>(new ErrorResponse(messages), HttpStatus.BAD_REQUEST);
    }
}

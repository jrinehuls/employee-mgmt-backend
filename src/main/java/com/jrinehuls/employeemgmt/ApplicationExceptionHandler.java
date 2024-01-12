package com.jrinehuls.employeemgmt;

import com.jrinehuls.employeemgmt.model.dto.error.ErrorResponse;
import com.jrinehuls.employeemgmt.exception.EmployeeConflictException;
import com.jrinehuls.employeemgmt.exception.EmployeeNotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(EmployeeNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(null);
        errorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

    @ExceptionHandler(EmployeeConflictException.class)
    protected ResponseEntity<ErrorResponse> handleEmployeeConflictException(EmployeeConflictException ex) {
        Map<String, List<String>> errors = new HashMap<>();
        String field = ex.getField();
        String message = ex.getMessage();
        errors.put(field, List.of(message));
        return new ResponseEntity<>(new ErrorResponse(errors), ex.getStatusCode());
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, List<String>> errors = new HashMap<>();
        for (ObjectError error: ex.getBindingResult().getAllErrors()) {
            FieldError fieldError = (FieldError) error;
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();

            if (!errors.containsKey(field)) {
                List<String> messages = new ArrayList<>();
                messages.add(message);
                errors.put(field, messages);
            } else {
                errors.get(field).add(message);
            }

        }

        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }


}

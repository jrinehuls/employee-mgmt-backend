package com.jrinehuls.employeemgmt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @GetMapping()
    public ResponseEntity<String> sayHi() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }
}

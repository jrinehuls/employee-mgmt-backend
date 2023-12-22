package com.jrinehuls.employeemgmt.service;

import com.jrinehuls.employeemgmt.dto.EmployeeDto;

public interface EmployeeService {

    public EmployeeDto createEmployee(EmployeeDto employeeDto);

    public EmployeeDto getEmployeeById(Long id);
}

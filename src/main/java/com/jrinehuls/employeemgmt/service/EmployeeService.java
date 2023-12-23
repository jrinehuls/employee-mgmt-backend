package com.jrinehuls.employeemgmt.service;

import com.jrinehuls.employeemgmt.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    public EmployeeDto createEmployee(EmployeeDto employeeDto);

    public EmployeeDto getEmployeeById(Long id);

    public List<EmployeeDto> getEmployees();

    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id);
}

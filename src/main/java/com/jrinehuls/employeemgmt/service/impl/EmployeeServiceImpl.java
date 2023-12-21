package com.jrinehuls.employeemgmt.service.impl;

import com.jrinehuls.employeemgmt.dto.EmployeeDto;
import com.jrinehuls.employeemgmt.entity.Employee;
import com.jrinehuls.employeemgmt.mapper.EmployeeMapper;
import com.jrinehuls.employeemgmt.repository.EmployeeRepository;
import com.jrinehuls.employeemgmt.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.mapDtoToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.mapEmployeeToDto(savedEmployee);
    }
}

package com.jrinehuls.employeemgmt.service.impl;

import com.jrinehuls.employeemgmt.dto.EmployeeDto;
import com.jrinehuls.employeemgmt.entity.Employee;
import com.jrinehuls.employeemgmt.exception.EmployeeConflictException;
import com.jrinehuls.employeemgmt.exception.EmployeeNotFoundException;
import com.jrinehuls.employeemgmt.mapper.EmployeeMapper;
import com.jrinehuls.employeemgmt.repository.EmployeeRepository;
import com.jrinehuls.employeemgmt.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.mapDtoToEmployee(employeeDto);
        Employee savedEmployee;
        try {
            savedEmployee = employeeRepository.save(employee);
        } catch (DataIntegrityViolationException e) {
            throw new EmployeeConflictException(this.getDataIntegrityMessage(e));
        }
        return employeeMapper.mapEmployeeToDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return employeeMapper.mapEmployeeToDto(employee);
    }

    @Override
    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employee -> employeeMapper.mapEmployeeToDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        try {
            Employee updatedEmployee = employeeRepository.save(employee);
            return employeeMapper.mapEmployeeToDto(updatedEmployee);
        } catch (DataIntegrityViolationException e) {
            throw new EmployeeConflictException(this.getDataIntegrityMessage(e));
        }
    }


    private String getDataIntegrityMessage(DataIntegrityViolationException e) {
        return e.getLocalizedMessage().split("\\*/ '", 2)[1].split("'", 2)[0];
    }
}

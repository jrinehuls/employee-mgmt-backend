package com.jrinehuls.employeemgmt.repository;

import com.jrinehuls.employeemgmt.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

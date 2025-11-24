package com.management.employee.service;

import com.management.employee.dto.EmployeeDTO;
import com.management.employee.entity.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO getEmployeeById(Long employeeId);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO deleteEmployeeById(Long employeeId);

    EmployeeDTO updateEmployeeDetails(Long employeeId, EmployeeDTO employeeDTO);
}

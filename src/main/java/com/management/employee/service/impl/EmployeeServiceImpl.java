package com.management.employee.service.impl;

import com.management.employee.dto.EmployeeDTO;
import com.management.employee.entity.Employee;
import com.management.employee.exception.ResourceNotFoundException;
import com.management.employee.mapper.EmployeeMapper;
import com.management.employee.repository.EmployeeRepository;
import com.management.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private static final String IdNotFoundErrorMessage = " : Employee Id does not exist or has been already deleted from Database";
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO){
        Employee employee = EmployeeMapper.mapToEmployee(employeeDTO);

        //Save to repo before returning to update the DB
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId){
        //First check in repo whether record exist or not
        Employee employee = employeeRepository.findById(employeeId).
                orElseThrow(()-> new ResourceNotFoundException(employeeId+IdNotFoundErrorMessage));

        //If found, return its DTO
        return EmployeeMapper.mapToEmployeeDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees(){
        List<Employee> employeesList = employeeRepository.findAll();

        //Convert one original object list to dto object list using streams, and then return List of DTOs
        return employeesList.stream().map((EmployeeMapper::mapToEmployeeDTO)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO deleteEmployeeById(Long employeeId){

        //If employee not found, nothing to delete
        Employee employee = employeeRepository.findById(employeeId).
                orElseThrow(()-> new ResourceNotFoundException(employeeId+IdNotFoundErrorMessage));

        EmployeeDTO deletedEmployee = EmployeeMapper.mapToEmployeeDTO(employee);

        //delete record from repo, then return the deleted object DTO to confirm
        employeeRepository.deleteById(employeeId);
        return deletedEmployee;
    }

    @Override
    public EmployeeDTO updateEmployeeDetails(Long employeeId, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(employeeId).
                orElseThrow(()-> new ResourceNotFoundException(employeeId+IdNotFoundErrorMessage));

        //If employee found, replace its details with new details
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());

        //Save new details to repo
        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDTO(updatedEmployee);
    }

}

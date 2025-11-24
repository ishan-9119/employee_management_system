package com.management.employee.controller;

import com.management.employee.dto.EmployeeDTO;
import com.management.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/ems")
public class EmployeeController {
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployees();
        return ResponseEntity.ok(employeeDTOList);
//      OR,  return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Long employeeId){
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO savedEmployee = employeeService.createNewEmployee(employeeDTO);
        return new ResponseEntity<>(savedEmployee,HttpStatus.CREATED);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployeeDetails(
            @PathVariable("id") Long employeeId,
            @RequestBody EmployeeDTO updatedEmployeeDTO){
        EmployeeDTO updatedEmployee = employeeService.updateEmployeeDetails(employeeId, updatedEmployeeDTO);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> deleteEmployeeById(@PathVariable("id") Long employeeID){
        EmployeeDTO deletedEmployee = employeeService.deleteEmployeeById(employeeID);
        return ResponseEntity.ok(deletedEmployee);
    }
}

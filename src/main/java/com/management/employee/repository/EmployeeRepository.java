package com.management.employee.repository;

import com.management.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository and @Transactional already processed by JpaRepository internally, no need to mention again
public interface EmployeeRepository extends JpaRepository<Employee, Long> {}

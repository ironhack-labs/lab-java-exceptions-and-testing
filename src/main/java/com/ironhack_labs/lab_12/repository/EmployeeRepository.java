package com.ironhack_labs.lab_12.repository;

import com.ironhack_labs.lab_12.domain.Employee;
import com.ironhack_labs.lab_12.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByStatus(Status status);
    List<Employee> findByDepartment(String department);
}

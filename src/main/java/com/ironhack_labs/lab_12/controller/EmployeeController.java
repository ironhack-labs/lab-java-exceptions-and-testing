package com.ironhack_labs.lab_12.controller;

import com.ironhack_labs.lab_12.domain.Employee;
import com.ironhack_labs.lab_12.domain.Status;
import com.ironhack_labs.lab_12.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeRepository repo;

    public EmployeeController(EmployeeRepository repo) { this.repo = repo; }

    // 1. Get all doctors
    @GetMapping
    public List<Employee> getAll() {
        return repo.findAll();
    }

    // 2. Get doctor by ID
    @GetMapping("/{id}")
    public Employee getById(@PathVariable("id") Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // 3. Get doctors by status
    @GetMapping("/status/{status}")
    public List<Employee> getByStatus(@PathVariable("status") Status status) {
        return repo.findByStatus(status);
    }

    // 4. Get doctors by department
    @GetMapping("/department/{department}")
    public List<Employee> getByDepartment(@PathVariable("department") String department) {
        return repo.findByDepartment(department);
    }
}

package com.example.hospital.controller;

import com.example.hospital.exceptions.NotFoundException;
import com.example.hospital.model.Employee;
import com.example.hospital.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Employee> getAllDoctors() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getDoctorById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Doctor with ID " + id + " not found"));
    }

    @GetMapping("/status/{status}")
    public List<Employee> getDoctorsByStatus(@PathVariable Employee.Status status) {
        return employeeRepository.findByStatus(status);
    }

    @GetMapping("/department/{department}")
    public List<Employee> getDoctorsByDepartment(@PathVariable String department) {
        return employeeRepository.findByDepartment(department);
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee with ID " + id + " not found"));
        existing.setName(employee.getName());
        existing.setDepartment(employee.getDepartment());
        existing.setStatus(employee.getStatus());
        return employeeRepository.save(existing);
    }


}

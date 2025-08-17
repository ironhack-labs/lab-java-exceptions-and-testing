package com.ironhack_labs.lab_12.controller;

import com.ironhack_labs.lab_12.domain.Patient;
import com.ironhack_labs.lab_12.domain.Status;
import com.ironhack_labs.lab_12.repository.PatientRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientRepository repo;

    public PatientController(PatientRepository repo) { this.repo = repo; }

    // 5. Get all patients
    @GetMapping
    public List<Patient> getAll() {
        return repo.findAll();
    }

    // 6. Get patient by ID
    @GetMapping("/{id}")
    public Patient getById(@PathVariable("id") Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    // 7. Get patients by date of birth range
    @GetMapping("/dob")
    public List<Patient> getByDobRange(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return repo.findByDateOfBirthBetween(start, end);
    }

    // 8. Get patients by admitting doctor's department
    @GetMapping("/admitting-department/{department}")
    public List<Patient> getByAdmittingDepartment(@PathVariable("department") String department) {
        return repo.findByAdmittedBy_Department(department);
    }

    // 9. Get all patients with a doctor whose status is OFF
    @GetMapping("/with-off-doctor")
    public List<Patient> getPatientsWithOffDoctor() {
        return repo.findByAdmittedBy_Status(Status.OFF);
    }
}

package com.example.hospital.controller;

import com.example.hospital.exceptions.NotFoundException;
import com.example.hospital.model.Patient;
import com.example.hospital.model.Employee;
import com.example.hospital.repository.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Patient not found with id " + id));
    }

    @GetMapping("/dob")
    public List<Patient> getPatientsByDateOfBirthRange(@RequestParam String start, @RequestParam String end) {
        return patientRepository.findByDateOfBirthBetween(LocalDate.parse(start), LocalDate.parse(end));
    }

    @GetMapping("/department/{department}")
    public List<Patient> getPatientsByDoctorDepartment(@PathVariable String department) {
        return patientRepository.findByAdmittedBy_Department(department);
    }

    @GetMapping("/doctor-off")
    public List<Patient> getPatientsWithDoctorOff() {
        return patientRepository.findByAdmittedBy_Status(Employee.Status.OFF);
    }

    @PostMapping("/patients")
    public Patient addPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    @PutMapping("/patients/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        Patient existing = patientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        existing.setName(patient.getName());
        existing.setDateOfBirth(patient.getDateOfBirth());
        existing.setAdmittedBy(patient.getAdmittedBy());
        return patientRepository.save(existing);
    }

}

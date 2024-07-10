package ironhack.schl.Lab4_06.controller.impl;

import ironhack.schl.Lab4_06.controller.interfaces.IPatientController;
import ironhack.schl.Lab4_06.model.Doctor;
import ironhack.schl.Lab4_06.model.Patient;
import ironhack.schl.Lab4_06.repository.DoctorRepository;
import ironhack.schl.Lab4_06.repository.PatientRepository;
import ironhack.schl.Lab4_06.service.interfaces.IDoctorService;
import ironhack.schl.Lab4_06.service.interfaces.IPatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PatientController implements IPatientController {

    @Autowired
    IPatientService patientService;

    @Autowired
    IDoctorService doctorService;

    Doctor doctor;

    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/patients/{patientId}")
    public Patient getPatientsById(@PathVariable Integer patientId) {
        return patientService.getPatientsById(patientId);
    }

    @GetMapping("/patients/dateOfBirth/{startDate}/{endDate}")
    public List<Patient> getPatientsByDateOfBirthRange(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
        return patientService.getPatientsByDateOfBirthRange(startDate, endDate);
    }
    // Get patients by admitting doctor's department

    @GetMapping("/patients/admittingDoctor/department/{department}")
    public List<Patient> getPatientsAdmittedByDoctorDepartment(@PathVariable String department) {
        return patientService.getPatientsAdmittedByDoctorDepartment(department);
    }

    // Get all patients with a doctor whose status is OFF

    @GetMapping("/patients/doctorStatus/OFF")
    public List<Patient> getPatientsByDoctorStatusOFF() {
        return patientService.getPatientsByDoctorStatusOFF();
    }

    // Create a new patient
    @PostMapping("/patients")
    @ResponseStatus(HttpStatus.CREATED)
    public void savePatient(@RequestBody @Valid Patient patient) {
        patientService.savePatient(patient);
    }

    // Update a patient's information
    @PutMapping("/patients/{patientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePatient(@RequestBody @Valid Patient patient, @PathVariable Integer patientId) {
        patientService.updatePatient(patient, patientId);
    }
}


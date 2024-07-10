package ironhack.schl.Lab4_06.service.impl;

import ironhack.schl.Lab4_06.model.Doctor;
import ironhack.schl.Lab4_06.model.Patient;
import ironhack.schl.Lab4_06.repository.DoctorRepository;
import ironhack.schl.Lab4_06.repository.PatientRepository;
import ironhack.schl.Lab4_06.service.interfaces.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService implements IPatientService {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    Doctor doctor;

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientsById(@PathVariable Integer patient_id) {
        Optional<Patient> patientOptional = patientRepository.findById(patient_id);
        if (patientOptional.isEmpty()) return null;
        return patientOptional.get();
    }

    @Override
    public List<Patient> getPatientsByDateOfBirthRange(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
        return patientRepository.findAll().stream()
                .filter(patient -> patient.getDateOfBirth().isAfter(startDate) && patient.getDateOfBirth().isBefore(endDate))
                .collect(Collectors.toList());
    }

    // Get patients by admitting doctor's department

    @Override
    public List<Patient> getPatientsAdmittedByDoctorDepartment(@PathVariable String department) {
        List<Integer> doctorIds = doctorRepository.findAll().stream()
                .filter(doctor -> doctor.getDepartment().equals(department))
                .map(Doctor::getEmployeeId)
                .collect(Collectors.toList());

        return patientRepository.findAll().stream()
                .filter(patient -> doctorIds.contains(patient.getAdmittedByDoctorId()))
                .collect(Collectors.toList());
    }

    // Get all patients with a doctor whose status is OFF

    @Override
    public List<Patient> getPatientsByDoctorStatusOFF() {
        List<Integer> doctorIds = doctorRepository.findAll().stream()
                .filter(doctor -> doctor.getStatus().equals("OFF"))
                .map(Doctor::getEmployeeId)
                .collect(Collectors.toList());

        return patientRepository.findAll().stream()
                .filter(patient -> doctorIds.contains(patient.getAdmittedByDoctorId()))
                .collect(Collectors.toList());
    }

    // Create a new patient
    @Override
    public void savePatient(Patient patient) {
        patientRepository.save(patient);
    }

    // Update a patient's information
    @Override
    public void updatePatient(Patient patient, Integer patientId) {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient " + patientId + " not found");
        patientRepository.save(patient);
    }
}

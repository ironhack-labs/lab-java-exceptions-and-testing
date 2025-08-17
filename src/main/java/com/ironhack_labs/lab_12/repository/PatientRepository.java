package com.ironhack_labs.lab_12.repository;

import com.ironhack_labs.lab_12.domain.Patient;
import com.ironhack_labs.lab_12.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    List<Patient> findByDateOfBirthBetween(LocalDate start, LocalDate end);
    List<Patient> findByAdmittedBy_Department(String department);
    List<Patient> findByAdmittedBy_Status(Status status);
}

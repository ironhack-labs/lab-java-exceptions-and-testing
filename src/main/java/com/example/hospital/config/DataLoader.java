package com.example.hospital.config;

import com.example.hospital.model.Employee;
import com.example.hospital.model.Patient;
import com.example.hospital.repository.EmployeeRepository;
import com.example.hospital.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final PatientRepository patientRepository;

    public DataLoader(EmployeeRepository employeeRepository, PatientRepository patientRepository) {
        this.employeeRepository = employeeRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) {
        Employee e1 = new Employee(356712L, "cardiology", "Alonso Flores", Employee.Status.ON_CALL);
        Employee e2 = new Employee(564134L, "immunology", "Sam Ortega", Employee.Status.ON);
        Employee e3 = new Employee(761527L, "cardiology", "German Ruiz", Employee.Status.OFF);
        Employee e4 = new Employee(166552L, "pulmonary", "Maria Lin", Employee.Status.ON);
        Employee e5 = new Employee(156545L, "orthopaedic", "Paolo Rodriguez", Employee.Status.ON_CALL);
        Employee e6 = new Employee(172456L, "psychiatric", "John Paul Armes", Employee.Status.OFF);

        employeeRepository.save(e1);
        employeeRepository.save(e2);
        employeeRepository.save(e3);
        employeeRepository.save(e4);
        employeeRepository.save(e5);
        employeeRepository.save(e6);

        patientRepository.save(new Patient(1L, "Jaime Jordan", LocalDate.of(1984, 3, 2), e2));
        patientRepository.save(new Patient(2L, "Marian Garcia", LocalDate.of(1972, 1, 12), e2));
        patientRepository.save(new Patient(3L, "Julia Dusterdieck", LocalDate.of(1954, 6, 11), e1));
        patientRepository.save(new Patient(4L, "Steve McDuck", LocalDate.of(1931, 11, 10), e3));
        patientRepository.save(new Patient(5L, "Marian Garcia", LocalDate.of(1999, 2, 15), e6));
    }
}

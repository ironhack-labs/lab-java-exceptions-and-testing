package com.example.hospital.controller;

import com.example.hospital.model.Employee;
import com.example.hospital.model.Patient;
import com.example.hospital.repository.EmployeeRepository;
import com.example.hospital.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PatientRepository patientRepository;

    @BeforeEach
    void setup() {
        patientRepository.deleteAll();
        employeeRepository.deleteAll();

        Employee doctor = new Employee(1L, "cardiology", "Dr. House", Employee.Status.ON);
        employeeRepository.save(doctor);

        patientRepository.save(new Patient(100L, "Juan Test", LocalDate.of(1990, 1, 1), doctor));
    }

    @Test
    void shouldReturnAllPatients() throws Exception {
        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldReturnPatientById() throws Exception {
        mockMvc.perform(get("/patients/100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Juan Test"));
    }

    @Test
    void shouldReturn404ForNonExistentPatient() throws Exception {
        mockMvc.perform(get("/patients/999"))
                .andExpect(status().isNotFound());
    }
}

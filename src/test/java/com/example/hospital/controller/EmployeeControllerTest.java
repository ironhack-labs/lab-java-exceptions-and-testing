package com.example.hospital.controller;

import com.example.hospital.model.Employee;
import com.example.hospital.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllDoctors() throws Exception {
        mockMvc.perform(get("/doctors"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetDoctorById_NotFound() throws Exception {
        mockMvc.perform(get("/doctors/99999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAddDoctor() throws Exception {
        Employee newDoctor = new Employee(null, "cardiology", "Test Doctor", Employee.Status.ON);

        mockMvc.perform(post("/doctors/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newDoctor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Doctor"));
    }

    @Test
    void testUpdateDoctor_NotFound() throws Exception {
        Employee update = new Employee(null, "neurology", "Updated Name", Employee.Status.OFF);
        mockMvc.perform(put("/doctors/employees/99999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isNotFound());
    }
}

package ironhack.schl.Lab4_06.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import ironhack.schl.Lab4_06.model.Patient;
import ironhack.schl.Lab4_06.repository.DoctorRepository;
import ironhack.schl.Lab4_06.repository.PatientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class PatientControllerTest {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    Patient patient;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Optional<Patient> patientOptional = patientRepository.findById(5);
        assertTrue(patientOptional.isPresent());
        patient = new Patient(6,"Julie Wilson");
    }

    @AfterEach
    void tearDown() {
        patientRepository.deleteById(6);
    }

    @Test
    void getAllCourses_validRequest_allCourses() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/patients"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Julie Wilson"));
    }

    @Test
    void getPatientById_validId_correctPatient() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/patients/6"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("6"));
    }

    @Test
    void getPatientById_invalidId_notFound() throws Exception {
        mockMvc.perform(get("/api/courses/1000").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void savePatient_validBody_patientSaved() throws Exception {
        String body = objectMapper.writeValueAsString(patient);

        mockMvc.perform(post("/api/patients").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(patientRepository.findAll().toString().contains("Julie Wilson"));
    }

}
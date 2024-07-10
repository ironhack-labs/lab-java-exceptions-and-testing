package ironhack.schl.Lab4_06.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import ironhack.schl.Lab4_06.controller.dto.DoctorDepartmentDTO;
import ironhack.schl.Lab4_06.model.Doctor;
import ironhack.schl.Lab4_06.repository.DoctorRepository;
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

import static ironhack.schl.Lab4_06.model.Status.ON;
import static org.junit.jupiter.api.Assertions.*;
        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class DoctorControllerTest {
    @Autowired
    DoctorRepository doctorRepository;

    //private Status status;


    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    Doctor doctor;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Optional<Doctor> doctorOptional = doctorRepository.findById(1);
        assertTrue(doctorOptional.isPresent());
        doctor = new Doctor(170552, "neurology","Antoni Vermeer", ON );
    }

    @AfterEach
    void tearDown() {
        doctorRepository.deleteById(170552);
    }

    @Test
    void getAllDoctors_validRequest_allDoctors() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/doctors"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Alonso Flores"));
    }

    @Test
    void getDoctorsById_validId_correctDoctor() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/doctors/356712"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Alonso Flores"));
    }

    @Test
    void getDoctorsById_invalidId_notFound() throws Exception {
        mockMvc.perform(get("/api/doctors/356713").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void saveDoctor_validBody_doctorSaved() throws Exception {
        String body = objectMapper.writeValueAsString(doctor);

        mockMvc.perform(post("/api/doctors").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(doctorRepository.findAll().toString().contains("cardiology"));
    }

    @Test
    void updateDoctor_validBody_doctorUpdated() throws Exception {
        doctor.setDepartment("AAA");

        String body = objectMapper.writeValueAsString(doctor);

        mockMvc.perform(put("/api/courses/department").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        assertTrue(doctorRepository.findAll().toString().contains("AAA"));
    }

    @Test
    void updateCourseHours_validBody_courseUpdated() throws Exception {
        DoctorDepartmentDTO doctorDepartmentDTO = new DoctorDepartmentDTO();

        String body = objectMapper.writeValueAsString(doctorDepartmentDTO);

        mockMvc.perform(patch("/api/doctors/department").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        assertTrue(doctorRepository.findAll().toString().contains("4999"));
    }
}
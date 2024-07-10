package ironhack.schl.Lab4_06.controller.interfaces;

import ironhack.schl.Lab4_06.model.Doctor;

import java.util.List;

public interface IDoctorController {
    List<Doctor> getAllDoctors();

    Doctor getDoctorsById(Integer id);

    List<Doctor> getDoctorsByStatus(String status);

    List<Doctor> getDoctorsByDepartment(String department);

    void saveDoctor(Doctor doctor);
}

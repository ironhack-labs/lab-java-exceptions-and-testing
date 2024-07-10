package ironhack.schl.Lab4_06.service.interfaces;

import ironhack.schl.Lab4_06.model.Doctor;
import ironhack.schl.Lab4_06.model.Status;

import java.util.List;

public interface IDoctorService {
    List<Doctor> getAllDoctors();

    Doctor getDoctorsById(Integer id);

    List<Doctor> getDoctorsByStatus(String status);

    List<Doctor> getDoctorsByDepartment(String department);

    void saveDoctor(Doctor doctor);

    void updateDoctorDepartment(String department, Integer employeeId);

    void updateDoctorStatus(Integer employeeId, Status newStatus);
}

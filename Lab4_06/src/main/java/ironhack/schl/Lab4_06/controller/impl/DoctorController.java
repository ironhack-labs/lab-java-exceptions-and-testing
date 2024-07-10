package ironhack.schl.Lab4_06.controller.impl;

import ironhack.schl.Lab4_06.controller.dto.DoctorDepartmentDTO;
import ironhack.schl.Lab4_06.controller.dto.DoctorStatusDTO;
import ironhack.schl.Lab4_06.controller.interfaces.IDoctorController;
import ironhack.schl.Lab4_06.model.Doctor;
import ironhack.schl.Lab4_06.model.Status;
import ironhack.schl.Lab4_06.service.interfaces.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DoctorController implements IDoctorController {

    @Autowired
    IDoctorService doctorService;
    private Status status;

    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/doctors/{employeeId}")
    public Doctor getDoctorsById(@PathVariable Integer employeeId) {
        return doctorService.getDoctorsById(employeeId);
    }
    // Get doctors by status

    @GetMapping("/doctors/status/{status}")
    public List<Doctor> getDoctorsByStatus(@PathVariable String status) {
        return doctorService.getDoctorsByStatus(status);
    }

    // Get doctors by department

    @GetMapping("/doctors/department/{department}")
    public List<Doctor> getDoctorsByDepartment(@PathVariable String department) {
        return doctorService.getDoctorsByDepartment(department);
    }

    // Create a new doctor
    @PostMapping("/doctors")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveDoctor(@RequestBody @Valid Doctor doctor) {
        doctorService.saveDoctor(doctor);
    }

    // Update  a doctor's status
    @PatchMapping("/doctors/status/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDoctorStatus(@RequestBody @Valid DoctorStatusDTO doctorStatusDTO, @PathVariable Integer employeeId) {
        doctorService.updateDoctorStatus(employeeId, doctorStatusDTO.getStatus());
    }

    // Update a doctor's department
    @PatchMapping("/doctors/department/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDoctorDepartment(@RequestBody @Valid DoctorDepartmentDTO doctorDepartmentDTO, @PathVariable Integer employeeId) {
        doctorService.updateDoctorDepartment(doctorDepartmentDTO.getDepartment(), employeeId);
    }
}
package ironhack.schl.Lab4_06.service.impl;

import ironhack.schl.Lab4_06.model.Doctor;
import ironhack.schl.Lab4_06.model.Status;
import ironhack.schl.Lab4_06.repository.DoctorRepository;
import ironhack.schl.Lab4_06.service.interfaces.IDoctorService;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService implements IDoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorsById(@PathVariable Integer employeeId) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(employeeId);
        if (doctorOptional.isEmpty()) return null;
        return doctorOptional.get();
    }

    @Override
    public List<Doctor> getDoctorsByStatus(@PathVariable String status) {
        return doctorRepository.findAll().stream()
                .filter(doctor -> doctor.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    // Get doctors by department

    @Override
    public List<Doctor> getDoctorsByDepartment(@PathVariable String department) {
        return doctorRepository.findAll().stream()
                .filter(doctor -> doctor.getDepartment().equals(department))
                .collect(Collectors.toList());
    }

    // Create a new doctor
    @Override
    public void saveDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    // Update  a doctor's status
    @Override
    public void updateDoctorStatus(Integer employeeId, Status newStatus) {
        Doctor doctor = doctorRepository.findById(employeeId).orElse(null);
        if (doctor != null) {
            doctor.setStatus(newStatus);
            doctorRepository.save(doctor);
        }
    }

    // Update a doctor's department
    @Override
    public void updateDoctorDepartment(String department, Integer employeeId) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(employeeId);
        if (doctorOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department " + employeeId + " not found");
        Doctor doctor = doctorOptional.get();
        doctor.setDepartment(department);
        doctorRepository.save(doctor);
    }
}

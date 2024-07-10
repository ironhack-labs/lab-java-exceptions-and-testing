package ironhack.schl.Lab4_06.service.interfaces;

import ironhack.schl.Lab4_06.model.Patient;

import java.time.LocalDate;
import java.util.List;

public interface IPatientService {
    List<Patient> getAllPatients();

    Patient getPatientsById(Integer id);

    List<Patient> getPatientsByDateOfBirthRange(LocalDate startDate, LocalDate endDate);

    List<Patient> getPatientsAdmittedByDoctorDepartment(String department);

    List<Patient> getPatientsByDoctorStatusOFF();

    void savePatient(Patient patient);

    void updatePatient(Patient patient, Integer patientId);
}

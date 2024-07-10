package ironhack.schl.Lab4_06.controller.dto;

import jakarta.validation.constraints.NotEmpty;

public class DoctorDepartmentDTO {
    @NotEmpty(message = "The department cannot be empty")
    private String department;

    public String getDepartment() {
        return department;
    }

}

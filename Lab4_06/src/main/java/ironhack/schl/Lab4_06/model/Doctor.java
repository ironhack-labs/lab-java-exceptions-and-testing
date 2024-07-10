package ironhack.schl.Lab4_06.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
    private String department;
    private String name;
    @NotEmpty(message = "Department cannot be empty")
    private Status status;

    public Integer getId() {
        return employeeId;
    }

    public String getDoctor() {
        return name;
    }
}

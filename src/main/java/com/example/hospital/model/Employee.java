package com.example.hospital.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    private Long employeeId;

    private String department;

    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        ON_CALL,
        ON,
        OFF
    }
}

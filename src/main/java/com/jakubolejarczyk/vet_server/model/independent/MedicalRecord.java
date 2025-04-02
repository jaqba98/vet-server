package com.jakubolejarczyk.vet_server.model.independent;

import com.jakubolejarczyk.vet_server.domain.independent.MedicalRecordDomain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "medicalrecord")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class MedicalRecord implements MedicalRecordDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_archived", nullable = false)
    private Boolean isArchived;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String diagnosis;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String treatment;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String procedures;

    @Column(name = "next_appointment", nullable = false)
    private LocalDate nextAppointment;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String notes;
}

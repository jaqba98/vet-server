package com.jakubolejarczyk.vet_server.model.independent;

import com.jakubolejarczyk.vet_server.domain.independent.MedicalRecordDomain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

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

    @Column(nullable = false)
    private String diagnosis;

    @Column(nullable = false)
    private String treatment;

    @Column(nullable = false)
    private String procedures;

    @Column(name = "next_appointment", nullable = false)
    private Date nextAppointment;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String notes;
}

package com.jakubolejarczyk.vet_server.model.dependent;

import com.jakubolejarczyk.vet_server.domain.dependent.MedicalRecordDomain;
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

    @Column(columnDefinition = "TEXT")
    private String diagnosis;

    @Column(columnDefinition = "TEXT")
    private String treatment;

    @Column(columnDefinition = "TEXT")
    private String procedures;

    @Column(name = "next_appointment")
    private LocalDate nextAppointment;

    @Column()
    private String status;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "appointment_id", nullable = false)
    private Long appointmentId;
}

package com.jakubolejarczyk.vet_server.model.dependent;

import com.jakubolejarczyk.vet_server.domain.dependent.AppointmentDomain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Appointment implements AppointmentDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_and_hour", nullable = false)
    private Timestamp dateAndHour;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String reason;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String notes;

    @Column(name = "clinic_id", nullable = false)
    private Long clinicId;

    @Column(name = "vet_id", nullable = false)
    private Long vetId;

    @Column(name = "pet_id", nullable = false)
    private Long petId;

    @Column(name = "invoice_id", nullable = false)
    private Long invoiceId;

    @Column(name = "medical_record_id", nullable = false)
    private Long medicalRecordId;
}

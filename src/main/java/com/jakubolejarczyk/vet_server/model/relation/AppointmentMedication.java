package com.jakubolejarczyk.vet_server.model.relation;

import com.jakubolejarczyk.vet_server.domain.relation.AppointmentMedicationDomain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class AppointmentMedication implements AppointmentMedicationDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "appointment_id", nullable = false)
    private Long appointmentId;

    @Column(name = "medication_id", nullable = false)
    private Long medicationId;
}

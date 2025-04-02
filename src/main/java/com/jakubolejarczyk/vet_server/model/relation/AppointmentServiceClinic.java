package com.jakubolejarczyk.vet_server.model.relation;

import com.jakubolejarczyk.vet_server.domain.relation.AppointmentServiceClinicDomain;
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
public class AppointmentServiceClinic implements AppointmentServiceClinicDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_archived", nullable = false)
    private Boolean isArchived;

    @Column(name = "appointment_id", nullable = false)
    private Long appointmentId;

    @Column(name = "service_clinic_id", nullable = false)
    private Long serviceClinicId;
}

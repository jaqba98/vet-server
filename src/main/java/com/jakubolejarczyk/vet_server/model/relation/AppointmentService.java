package com.jakubolejarczyk.vet_server.model.relation;

import com.jakubolejarczyk.vet_server.domain.relation.AppointmentServiceDomain;
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
public class AppointmentService implements AppointmentServiceDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_archived", nullable = false)
    private Boolean isArchived;

    @Column(name = "appointment_id", nullable = false)
    private Long appointmentId;

    @Column(name = "service_id", nullable = false)
    private Long serviceId;
}

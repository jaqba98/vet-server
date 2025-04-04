package com.jakubolejarczyk.vet_server.dto.metadata.relation;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class AppointmentMedicationMetadata {
    private BaseMetadata id;

    private BaseMetadata appointmentId;

    private BaseMetadata medicationId;
}

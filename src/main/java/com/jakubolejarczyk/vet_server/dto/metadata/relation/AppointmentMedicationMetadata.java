package com.jakubolejarczyk.vet_server.dto.metadata.relation;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class AppointmentMedicationMetadata {
    private BaseMetadata id;

    private BaseMetadata appointmentId;

    private BaseMetadata medicationId;
}

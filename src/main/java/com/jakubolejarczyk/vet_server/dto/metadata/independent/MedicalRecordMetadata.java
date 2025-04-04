package com.jakubolejarczyk.vet_server.dto.metadata.independent;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class MedicalRecordMetadata {
    private BaseMetadata id;

    private BaseMetadata isArchived;

    private BaseMetadata diagnosis;

    private BaseMetadata treatment;

    private BaseMetadata procedures;

    private BaseMetadata nextAppointment;

    private BaseMetadata status;

    private BaseMetadata notes;
}

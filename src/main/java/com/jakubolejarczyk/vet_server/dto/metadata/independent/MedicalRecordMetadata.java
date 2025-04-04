package com.jakubolejarczyk.vet_server.dto.metadata.independent;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
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

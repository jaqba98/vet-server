package com.jakubolejarczyk.vet_server.dto.metadata.dependent;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceClinicMetadata {
    private BaseMetadata id;

    private BaseMetadata fullName;

    private BaseMetadata description;

    private BaseMetadata category;

    private BaseMetadata durationMinutes;

    private BaseMetadata price;

    private BaseMetadata isAvailable;

    private BaseMetadata clinicId;
}

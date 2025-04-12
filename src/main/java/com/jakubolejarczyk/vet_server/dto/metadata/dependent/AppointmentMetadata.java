package com.jakubolejarczyk.vet_server.dto.metadata.dependent;

import com.jakubolejarczyk.vet_server.dto.metadata.base.BaseMetadata;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppointmentMetadata {
    private BaseMetadata clinicId;

    private BaseMetadata petId;

    private BaseMetadata vetId;
}

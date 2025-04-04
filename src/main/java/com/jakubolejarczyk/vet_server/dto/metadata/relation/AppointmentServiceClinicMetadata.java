package com.jakubolejarczyk.vet_server.dto.metadata.relation;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppointmentServiceClinicMetadata {
    private BaseMetadata id;

    private BaseMetadata appointmentId;

    private BaseMetadata serviceClinicId;
}

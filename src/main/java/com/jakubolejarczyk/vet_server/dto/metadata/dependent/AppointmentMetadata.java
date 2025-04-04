package com.jakubolejarczyk.vet_server.dto.metadata.dependent;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class AppointmentMetadata {
    private BaseMetadata id;

    private BaseMetadata dateAndHour;

    private BaseMetadata type;

    private BaseMetadata status;

    private BaseMetadata reason;

    private BaseMetadata notes;

    private BaseMetadata clinicId;

    private BaseMetadata vetId;

    private BaseMetadata petId;

    private BaseMetadata invoiceId;

    private BaseMetadata medicalRecordId;
}

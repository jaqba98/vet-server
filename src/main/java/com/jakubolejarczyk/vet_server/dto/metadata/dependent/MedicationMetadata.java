package com.jakubolejarczyk.vet_server.dto.metadata.dependent;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class MedicationMetadata {
    private BaseMetadata id;

    private BaseMetadata fullName;

    private BaseMetadata description;

    private BaseMetadata manufacturer;

    private BaseMetadata dose;

    private BaseMetadata quantityInStock;

    private BaseMetadata expirationDate;

    private BaseMetadata price;

    private BaseMetadata isAvailable;

    private BaseMetadata clinicId;
}

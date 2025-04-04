package com.jakubolejarczyk.vet_server.dto.metadata.dependent;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class VetMetadata {
    private BaseMetadata id;

    private BaseMetadata licenseNumber;

    private BaseMetadata licenseIssueDate;

    private BaseMetadata licenseExpiryDate;

    private BaseMetadata specialization;

    private BaseMetadata yearsOfExperience;

    private BaseMetadata accountId;

    private BaseMetadata openingHourId;
}

package com.jakubolejarczyk.vet_server.dto.metadata.dependent;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class EmploymentMetadata {
    private BaseMetadata id;

    private BaseMetadata isOwner;

    private BaseMetadata accountId;

    private BaseMetadata clinicId;
}

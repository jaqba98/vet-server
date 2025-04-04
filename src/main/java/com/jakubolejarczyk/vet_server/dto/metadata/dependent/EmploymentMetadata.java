package com.jakubolejarczyk.vet_server.dto.metadata.dependent;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmploymentMetadata {
    private BaseMetadata id;

    private BaseMetadata isOwner;

    private BaseMetadata accountId;

    private BaseMetadata clinicId;
}

package com.jakubolejarczyk.vet_server.dto.metadata.dependent;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ClientMetadata {
    private BaseMetadata id;

    private BaseMetadata email;

    private BaseMetadata phoneNumber;

    private BaseMetadata firstName;

    private BaseMetadata lastName;

    private BaseMetadata clinicId;
}

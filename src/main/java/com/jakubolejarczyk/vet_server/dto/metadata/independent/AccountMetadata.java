package com.jakubolejarczyk.vet_server.dto.metadata.independent;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class AccountMetadata {
    private BaseMetadata id;

    private BaseMetadata email;

    private BaseMetadata password;

    private BaseMetadata firstName;

    private BaseMetadata lastName;

    private BaseMetadata role;

    private BaseMetadata pictureUrl;
}

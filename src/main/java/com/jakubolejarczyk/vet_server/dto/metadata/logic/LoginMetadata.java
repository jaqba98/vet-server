package com.jakubolejarczyk.vet_server.dto.metadata.logic;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class LoginMetadata {
    private BaseMetadata email;

    private BaseMetadata password;
}

package com.jakubolejarczyk.vet_server.dto.metadata;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class ClientMetadata {
    private Map<Long, String> clinicId;
}

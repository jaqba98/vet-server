package com.jakubolejarczyk.vet_server.dto.metadata.logic;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChooseRoleMetadata {
    private BaseMetadata role;
}

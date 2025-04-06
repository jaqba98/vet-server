package com.jakubolejarczyk.vet_server.dto.metadata.dependent;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClinicMetadata {
    private List<Long> myClinicIds;
}

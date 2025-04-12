package com.jakubolejarczyk.vet_server.dto.metadata.dependent;

import com.jakubolejarczyk.vet_server.dto.metadata.base.BaseMetadata;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OpeningHourMetadata {
    private List<Long> myOpeningHourIds;

    private BaseMetadata clinicId;
}

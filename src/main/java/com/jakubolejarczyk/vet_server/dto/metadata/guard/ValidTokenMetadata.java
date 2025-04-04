package com.jakubolejarczyk.vet_server.dto.metadata.guard;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ValidTokenMetadata {
}

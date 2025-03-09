package com.jakubolejarczyk.vet_server.dto.response.clinic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ClinicCreateResponseDto {
    private Boolean success;
}

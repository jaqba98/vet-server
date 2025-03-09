package com.jakubolejarczyk.vet_server.dto.response.clinic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ClinicCreateResponseDto {
    private Boolean success;

    private ArrayList<String> errors;
}

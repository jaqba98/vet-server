package com.jakubolejarczyk.vet_server.dto.response.controller.clinic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class ClinicDeleteResponseDto {
    private Boolean success;

    private ArrayList<String> errors;
}

package com.jakubolejarczyk.vet_server.dto.response.registration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class RegistrationResponseDto {
    private Boolean success;

    private ArrayList<String> errors;
}

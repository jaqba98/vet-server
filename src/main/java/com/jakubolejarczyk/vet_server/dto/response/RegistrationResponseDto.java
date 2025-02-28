package com.jakubolejarczyk.vet_server.dto.response;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegistrationResponseDto {
    private Boolean success;

    private ArrayList<String> errors;
}

package com.jakubolejarczyk.vet_server.dto.response.handler;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ValidationHandlerResponseDto {
    private Boolean success;

    private ArrayList<String> errors;
}

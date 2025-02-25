package com.jakubolejarczyk.vet_server.dto.request;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class AuthRequestDto {
    @NotNull(message = "Token must be defined!")
    @NotBlank(message = "Token cannot be empty!")
    private String token;
}

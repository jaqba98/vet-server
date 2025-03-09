package com.jakubolejarczyk.vet_server.dto.request.controller;

import com.jakubolejarczyk.vet_server.validator.token.Token;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChooseRoleRequestDto {
    @NotNull(message = "Token is requires!")
    @NotBlank(message = "Token cannot be empty!")
    @Token
    private String token;

    @NotNull(message = "Role is requires!")
    @NotBlank(message = "Role cannot be empty!")
    @Pattern(regexp = "^(vet|client)$", message = "Role must be either 'vet' or 'client'!")
    private String role;
}

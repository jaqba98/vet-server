package com.jakubolejarczyk.vet_server.dto.request.controller;

import com.jakubolejarczyk.vet_server.dto.base.TokenRequestDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChooseRoleRequestDto extends TokenRequestDto {
    @NotNull(message = "Role is requires!")
    @NotBlank(message = "Role cannot be empty!")
    @Pattern(regexp = "^(vet|client)$", message = "Role must be either 'vet' or 'client'!")
    private String role;
}

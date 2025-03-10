package com.jakubolejarczyk.vet_server.dto.request.controller.clinic;

import com.jakubolejarczyk.vet_server.validator.token.Token;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicReadRequestDto {
    @NotNull(message = "Token is requires!")
    @NotBlank(message = "Token cannot be empty!")
    @Token
    private String token;
}

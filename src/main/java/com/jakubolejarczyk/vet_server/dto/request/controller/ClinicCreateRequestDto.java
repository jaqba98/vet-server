package com.jakubolejarczyk.vet_server.dto.request.controller;

import com.jakubolejarczyk.vet_server.validator.token.Token;
import com.jakubolejarczyk.vet_server.validator.unique.Unique;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicCreateRequestDto {
    @NotNull(message = "Token is requires!")
    @NotBlank(message = "Token cannot be empty!")
    @Token
    private String token;

    @NotNull(message = "Name is requires!")
    @NotBlank(message = "Name cannot be empty!")
    @Unique(message = "There is a clinic with the given name!", table = "clinic", column = "name")
    private String name;
}

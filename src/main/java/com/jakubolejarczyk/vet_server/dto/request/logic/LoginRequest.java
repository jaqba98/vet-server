package com.jakubolejarczyk.vet_server.dto.request.logic;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotNull(message = "Email is required!")
    private String email;

    @NotNull(message = "Password is required!")
    private String password;
}

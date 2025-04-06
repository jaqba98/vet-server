package com.jakubolejarczyk.vet_server.dto.request.base;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRequest {
    @NotNull(message = "Token is required!")
    private String token;
}

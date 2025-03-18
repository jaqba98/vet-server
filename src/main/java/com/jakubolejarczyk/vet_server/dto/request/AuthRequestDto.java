package com.jakubolejarczyk.vet_server.dto.request;

import com.jakubolejarczyk.vet_server.validator.token.Token;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@Setter
@SuperBuilder
public class AuthRequestDto {
    @NotNull(message = "Token is required!")
    @NotBlank(message = "Token cannot be empty!")
    @Token
    private String token;
}

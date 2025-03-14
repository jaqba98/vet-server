package com.jakubolejarczyk.vet_server.dto.request.guard;

import com.jakubolejarczyk.vet_server.validator.token.Token;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IsClientRequestDto {
    @NotNull(message = "Token is requires!")
    @NotBlank(message = "Token cannot be empty!")
    @Token
    private String token;
}

package com.jakubolejarczyk.vet_server.dto.request.guard;

import com.jakubolejarczyk.vet_server.validator.token.Token;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidTokenRequest {
    @Token
    private String token;
}

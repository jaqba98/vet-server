package com.jakubolejarczyk.vet_server.dto.request.base;

import com.jakubolejarczyk.vet_server.validator.token.Token;
import lombok.Data;

@Data
public class TokenRequest {
    @Token
    private String token;
}

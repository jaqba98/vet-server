package com.jakubolejarczyk.vet_server.dto.request.base;

import com.jakubolejarczyk.vet_server.validator.token.Token;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRequestDto {
    @Token
    private String token;
}

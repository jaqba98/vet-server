package com.jakubolejarczyk.vet_server.validator.token;

import com.jakubolejarczyk.vet_server.security.TokenService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TokenValidator implements ConstraintValidator<Token, String> {
    private final TokenService tokenService;

    @Override
    public boolean isValid(String token, ConstraintValidatorContext constraintValidatorContext) {
        return tokenService.verify(token);
    }
}

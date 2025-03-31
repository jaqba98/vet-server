package com.jakubolejarczyk.vet_server.validator.email_not_exist;

import com.jakubolejarczyk.vet_server.service.independent.AccountService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmailNotExistValidator implements ConstraintValidator<EmailNotExist, String> {
    private final AccountService accountService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return accountService.findByEmail(email).isEmpty();
    }
}

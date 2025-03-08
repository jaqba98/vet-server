package com.jakubolejarczyk.vet_server.validator.email_not_exist;

import com.jakubolejarczyk.vet_server.service.database.AccountService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmailNotExistValidator implements ConstraintValidator<EmailNotExist, String> {
    private final AccountService accountService;

    @Override
    public void initialize(EmailNotExist annotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return accountService.isNotAccountByEmail(value);
    }
}

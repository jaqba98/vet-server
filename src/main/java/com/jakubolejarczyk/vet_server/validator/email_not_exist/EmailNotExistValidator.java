package com.jakubolejarczyk.vet_server.validator.email_not_exist;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

import com.jakubolejarczyk.vet_server.service.database.AccountService;

@AllArgsConstructor
public class EmailNotExistValidator implements ConstraintValidator<EmailNotExist, String> {
    private final AccountService accountService;

    @Override
    public void initialize(EmailNotExist annotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println(context.getClass());
        return accountService.isNotAccountByEmail(value);
    }
}

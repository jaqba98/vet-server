package com.jakubolejarczyk.vet_server.validator.token;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TokenValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {
    String message() default "Invalid token!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

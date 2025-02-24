package com.jakubolejarczyk.vet_server.validator.email_not_exist;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Constraint(validatedBy = EmailNotExistValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailNotExist {
    String message() default "Email already exists!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

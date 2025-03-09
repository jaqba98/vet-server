package com.jakubolejarczyk.vet_server.validator.fields_match;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

public class FieldsMatchValidator implements ConstraintValidator<FieldsMatch, Object> {
    private String first;

    private String second;

    @Override
    public void initialize(FieldsMatch annotation) {
        this.first = annotation.first();
        this.second = annotation.second();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Field firstField = value.getClass().getDeclaredField(first);
            Field secondField = value.getClass().getDeclaredField(second);
            firstField.setAccessible(true);
            secondField.setAccessible(true);
            Object firstValue = firstField.get(value);
            Object secondValue = secondField.get(value);
            return firstValue != null && firstValue.equals(secondValue);
        } catch (Exception e) {
            return false;
        }
    }
}

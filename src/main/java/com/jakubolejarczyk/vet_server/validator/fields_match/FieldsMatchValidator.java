package com.jakubolejarczyk.vet_server.validator.fields_match;

import java.lang.reflect.Field;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FieldsMatchValidator implements ConstraintValidator<FieldsMatch, Object> {
    private String firstField;
    private String secondField;

    @Override
    public void initialize(FieldsMatch annotation) {
        this.firstField = annotation.first();
        this.secondField = annotation.second();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Field first = value.getClass().getDeclaredField(firstField);
            Field second = value.getClass().getDeclaredField(secondField);
            first.setAccessible(true);
            second.setAccessible(true);
            Object firstValue = first.get(value);
            Object secondValue = second.get(value);
            return firstValue != null && firstValue.equals(secondValue);
        } catch (Exception e) {
            return false;
        }
    }
}

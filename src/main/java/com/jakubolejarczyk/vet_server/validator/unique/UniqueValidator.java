package com.jakubolejarczyk.vet_server.validator.unique;

import com.jakubolejarczyk.vet_server.service.database.ClinicService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UniqueValidator implements ConstraintValidator<Unique, String> {
    private final ClinicService clinicService;

    private String table;

    private String column;

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.table = constraintAnnotation.table();
        this.column = constraintAnnotation.column();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (table.equals("clinic")) {
            return clinicService.unique(column, value);
        }
        throw new Error("Unsupported table!");
    }
}

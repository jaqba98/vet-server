package com.jakubolejarczyk.vet_server.validator.unique;

import com.jakubolejarczyk.vet_server.service.database.ClinicService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniqueValidator implements ConstraintValidator<Unique, String> {
    private final ClinicService clinicService;

    private String table;

    private String column;

    @Autowired
    public UniqueValidator(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.table = constraintAnnotation.table();
        this.column = constraintAnnotation.column();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (table.equals("clinic")) {
            if (column.equals("name")) {
                return clinicService.uniqueName(value);
            }
        }
        throw new Error("Unsupported table or column!");
    }
}

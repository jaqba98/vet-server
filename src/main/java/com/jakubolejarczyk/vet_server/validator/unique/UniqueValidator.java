package com.jakubolejarczyk.vet_server.validator.unique;

import com.jakubolejarczyk.vet_server.service.dependent.ClientService;
import com.jakubolejarczyk.vet_server.service.dependent.ClinicService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueValidator implements ConstraintValidator<Unique, String> {
    private final ClinicService clinicService;
    private final ClientService clientService;

    @Autowired
    public UniqueValidator(ClinicService clinicService, ClientService clientService) {
        this.clinicService = clinicService;
        this.clientService = clientService;
    }

    private String table;

    private String column;

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.table = constraintAnnotation.table();
        this.column = constraintAnnotation.column();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (table.equals("clinic") && column.equals("name")) {
            return clinicService.findByName(value).isEmpty();
        }
        if (table.equals("client") && column.equals("email")) {
            return clientService.findByEmail(value).isEmpty();
        }
        if (table.equals("client") && column.equals("phoneNumber")) {
            return clientService.findByPhoneNumber(value).isEmpty();
        }
        throw new Error("Unsupported table or column!");
    }
}

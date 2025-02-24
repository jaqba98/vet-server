package com.jakubolejarczyk.vet_server.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ErrorHandlerUtil {
    public Map<String, String> getErrors(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
        }
        return errors;
    }
}

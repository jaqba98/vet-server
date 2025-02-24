package com.jakubolejarczyk.vet_server.util;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Service
public class ErrorHandlerUtil {
    public Map<String, String> getErrors(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            }
        }
        return errors;
    }
}

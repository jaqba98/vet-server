package com.jakubolejarczyk.vet_server.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Service
public class ErrorHandlerUtil {
    public Map<String, ArrayList<String>> getErrors(BindingResult result) {
        Map<String, ArrayList<String>> errors = new HashMap<>();
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                if (error instanceof FieldError) {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    ArrayList<String> values;
                    if (errors.containsKey(fieldName)) {
                        values = errors.get(fieldName);
                    } else {
                        values = new ArrayList<>();
                    }
                    values.add(errorMessage);
                    errors.put(fieldName, values);
                } else {
                    String fieldName = "form";
                    String errorMessage = error.getDefaultMessage();
                    ArrayList<String> values;
                    if (errors.containsKey(fieldName)) {
                        values = errors.get(fieldName);
                    } else {
                        values = new ArrayList<>();
                    }
                    values.add(errorMessage);
                    errors.put(fieldName, values);
                }
            }
        }
        return errors;
    }
}

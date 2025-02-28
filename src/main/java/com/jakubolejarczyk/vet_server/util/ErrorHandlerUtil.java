package com.jakubolejarczyk.vet_server.util;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

@Service
public class ErrorHandlerUtil {
    public ArrayList<String> getErrors(BindingResult result) {
        ArrayList<String> errors = new ArrayList<>();
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                String errorMessage = error.getDefaultMessage();
                errors.add(errorMessage);
            }
        }
        return errors;
    }
}

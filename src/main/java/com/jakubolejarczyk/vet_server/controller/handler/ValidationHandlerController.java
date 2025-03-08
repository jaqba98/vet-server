package com.jakubolejarczyk.vet_server.controller.handler;

import com.jakubolejarczyk.vet_server.dto.response.handler.ValidationHandlerResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
@AllArgsConstructor
public class ValidationHandlerController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationHandlerResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        ArrayList<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String message = error.getDefaultMessage();
            errors.add(message);
        });
        ValidationHandlerResponseDto responseDto = ValidationHandlerResponseDto
                .builder()
                .success(false)
                .errors(errors)
                .build();
        return ResponseEntity.badRequest().body(responseDto);
    }
}

package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.dto.request.registration.RegistrationRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.registration.RegistrationResponseDto;
import com.jakubolejarczyk.vet_server.service.database.AccountService;
import com.jakubolejarczyk.vet_server.service.security.PasswordService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class RegistrationController {
    private final AccountService accountService;

    private final PasswordService passwordService;

    @PostMapping("registration")
    public ResponseEntity<RegistrationResponseDto> registration(@Valid @RequestBody RegistrationRequestDto requestDto) {
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();
        String firstName = requestDto.getFirstName();
        String lastName = requestDto.getLastName();
        String hashPassword = passwordService.encode(password);
        accountService.create(email, hashPassword, firstName, lastName);
        RegistrationResponseDto responseDto = new RegistrationResponseDto(true, new ArrayList<>());
        return ResponseEntity.ok().body(responseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RegistrationResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        ArrayList<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String message = error.getDefaultMessage();
            errors.add(message);
        });
        RegistrationResponseDto responseDto = new RegistrationResponseDto(false, errors);
        return ResponseEntity.ok().body(responseDto);
    }
}

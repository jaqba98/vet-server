package com.jakubolejarczyk.vet_server.controller;

import java.util.HashMap;
import java.util.Map;
import com.jakubolejarczyk.vet_server.dto.request.RegistrationRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.RegistrationResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jakubolejarczyk.vet_server.service.AccountService;
import com.jakubolejarczyk.vet_server.util.ErrorHandlerUtil;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class RegistrationController {
    private final ErrorHandlerUtil errorHandlerUtil;

    private final AccountService accountService;

    @PostMapping("registration")
    public ResponseEntity<?> registration(@Valid @RequestBody RegistrationRequestDto request, BindingResult result) {
        Map<String, String> errors = errorHandlerUtil.getErrors(result);
        if (!errors.isEmpty()) {
            RegistrationResponseDto response = new RegistrationResponseDto(false, errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        String email = request.getEmail();
        String password = request.getPassword();
        String confirmPassword = request.getConfirmPassword();
        String firstName = request.getFirstName();
        String lastName = request.getLastName();
        String role = request.getRole();
        Map<String, String> emailErrors = accountService.isAccountByEmail(email);
        if (!emailErrors.isEmpty()) {
            RegistrationResponseDto response = new RegistrationResponseDto(false, emailErrors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        accountService.createAccount(email, password, firstName, lastName, role);
        RegistrationResponseDto response = new RegistrationResponseDto(true, new HashMap<>());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

package com.jakubolejarczyk.vet_server.controller;

// todo: I am here
import java.util.ArrayList;
import java.util.Map;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jakubolejarczyk.vet_server.service.database.AccountService;
import com.jakubolejarczyk.vet_server.util.ErrorHandlerUtil;
import com.jakubolejarczyk.vet_server.dto.request.RegistrationRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.RegistrationResponseDto;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class RegistrationController {
    private final ErrorHandlerUtil errorHandlerUtil;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("registration")
    public ResponseEntity<RegistrationResponseDto> registration(
            @Valid @RequestBody RegistrationRequestDto request,
            BindingResult result
    ) {
        Map<String, ArrayList<String>> errors = errorHandlerUtil.getErrors(result);
        if (!errors.isEmpty()) {
            RegistrationResponseDto response = new RegistrationResponseDto(false, errors);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        String email = request.getEmail();
        String password = request.getPassword();
        String firstName = request.getFirstName();
        String lastName = request.getLastName();
        String role = request.getRole();
        String hashPassword = passwordEncoder.encode(password);
        accountService.createAccount(email, hashPassword, firstName, lastName, role);
        RegistrationResponseDto response = new RegistrationResponseDto(true, errors);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

package com.jakubolejarczyk.vet_server.controller;

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

import com.jakubolejarczyk.vet_server.util.ErrorHandlerUtil;
import com.jakubolejarczyk.vet_server.service.database.AccountService;
import com.jakubolejarczyk.vet_server.dto.response.RegistrationResponseDto;
import com.jakubolejarczyk.vet_server.dto.request.RegistrationRequestDto;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class RegistrationController {
    private final ErrorHandlerUtil errorHandlerUtil;

    private final AccountService accountService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("registration")
    public ResponseEntity<RegistrationResponseDto> registration(
            @Valid @RequestBody RegistrationRequestDto requestDto,
            BindingResult result
    ) {
        ArrayList<String> errors = errorHandlerUtil.getErrors(result);
        if (!errors.isEmpty()) {
            RegistrationResponseDto response = RegistrationResponseDto.builder().success(false).errors(errors).build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();
        String firstName = requestDto.getFirstName();
        String lastName = requestDto.getLastName();
        String hashPassword = passwordEncoder.encode(password);
        accountService.createAccount(email, hashPassword, firstName, lastName);
        RegistrationResponseDto responseDto = RegistrationResponseDto.builder().success(true).errors(errors).build();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}

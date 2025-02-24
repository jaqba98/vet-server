package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.dto.response.RegistrationResponseDto;
import com.jakubolejarczyk.vet_server.service.AccountService;
import com.jakubolejarczyk.vet_server.util.ErrorHandlerUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.jakubolejarczyk.vet_server.dto.request.RegistrationRequestDto;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class RegistrationController {
    private final AccountService accountService;

    private final ErrorHandlerUtil errorHandlerUtil;

    @Autowired
    public RegistrationController(
            AccountService accountService,
            ErrorHandlerUtil errorHandlerUtil
    ) {
        this.accountService = accountService;
        this.errorHandlerUtil = errorHandlerUtil;
    }

    @PostMapping("registration")
    public ResponseEntity<?> registration(@Valid @RequestBody RegistrationRequestDto request, BindingResult result) {
        Map<String, String> errors = errorHandlerUtil.getErrors(result);
        if (!errors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(request);
        }
        String email = request.getEmail();
        String password = request.getPassword();
        String firstName = request.getFirstName();
        String lastName = request.getLastName();
        String role = request.getRole();
        Boolean success = accountService.createAccount(email, password, firstName, lastName, role);
        RegistrationResponseDto response = new RegistrationResponseDto();
        if (success) {
            response.setSuccess(true);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        response.setSuccess(false);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}

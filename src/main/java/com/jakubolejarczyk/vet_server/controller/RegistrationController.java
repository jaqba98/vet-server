package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.dto.request.controller.RegistrationRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
import com.jakubolejarczyk.vet_server.service.security.PasswordService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ResponseDto<?>> registration(@Valid @RequestBody RegistrationRequestDto requestDto) {
        val email = requestDto.getEmail();
        val password = requestDto.getPassword();
        val firstName = requestDto.getFirstName();
        val lastName = requestDto.getLastName();
        val hashPassword = passwordService.encode(password);
        val messages = new ArrayList<String>();
        accountService.create(email, hashPassword, firstName, lastName);
        messages.add("The account has been created successfully!");
        val responseDto = new ResponseDto<>(true, messages, null);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<String>> handleValidation(MethodArgumentNotValidException ex) {
        val errors = new ArrayList<String>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String message = error.getDefaultMessage();
            errors.add(message);
        });
        val responseDto = new ResponseDto<>(false, errors, "");
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}

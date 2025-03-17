package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.dto.request.controller.RegistrationRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.service.database.AccountService;
import com.jakubolejarczyk.vet_server.service.security.PasswordService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<ResponseDto<String>> registration(@Valid @RequestBody RegistrationRequestDto requestDto) {
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();
        String firstName = requestDto.getFirstName();
        String lastName = requestDto.getLastName();
        String hashPassword = passwordService.encode(password);
        accountService.create(email, hashPassword, firstName, lastName);
        ResponseDto<String> responseDto = new ResponseDto<>(
                true, new ArrayList<>(), "The account has been created successfully!"
        );
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<String>> handleValidation(MethodArgumentNotValidException ex) {
        ArrayList<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String message = error.getDefaultMessage();
            errors.add(message);
        });
        ResponseDto<String> responseDto = new ResponseDto<>(false, errors, "");
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}

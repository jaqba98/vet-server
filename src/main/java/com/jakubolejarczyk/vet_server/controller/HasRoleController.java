package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.dto.request.guard.HasRoleRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.guard.HasRoleResponseDto;
import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
import com.jakubolejarczyk.vet_server.service.security.TokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class HasRoleController {
    private final TokenService tokenService;

    private final AccountService accountService;

    @PostMapping("has-role")
    public ResponseEntity<HasRoleResponseDto> hasRole(@Valid @RequestBody HasRoleRequestDto requestDto) {
        String token = requestDto.getToken();
        String email = tokenService.decode(token);
        Optional<Account> account = accountService.findByEmail(email);
        if (account.isEmpty()) {
            HasRoleResponseDto responseDto = new HasRoleResponseDto(false, new ArrayList<>());
            return ResponseEntity.ok().body(responseDto);
        }
        String role = account.get().getRole();
        if (role == null) {
            HasRoleResponseDto responseDto = new HasRoleResponseDto(false, new ArrayList<>());
            return ResponseEntity.ok().body(responseDto);
        }
        HasRoleResponseDto responseDto = new HasRoleResponseDto(true, new ArrayList<>());
        return ResponseEntity.ok().body(responseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HasRoleResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        ArrayList<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String message = error.getDefaultMessage();
            errors.add(message);
        });
        HasRoleResponseDto responseDto = new HasRoleResponseDto(false, errors);
        return ResponseEntity.ok().body(responseDto);
    }
}

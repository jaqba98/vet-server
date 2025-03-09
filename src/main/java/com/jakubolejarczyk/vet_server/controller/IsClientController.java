package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.dto.request.guard.IsClientRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.guard.IsClientResponseDto;
import com.jakubolejarczyk.vet_server.model.Account;
import com.jakubolejarczyk.vet_server.service.database.AccountService;
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
public class IsClientController {
    private final TokenService tokenService;

    private final AccountService accountService;

    @PostMapping("is-client")
    public ResponseEntity<IsClientResponseDto> isClient(@Valid @RequestBody IsClientRequestDto requestDto) {
        String token = requestDto.getToken();
        String email = tokenService.decode(token);
        Optional<Account> account = accountService.findByEmail(email);
        if (account.isEmpty()) {
            IsClientResponseDto responseDto = new IsClientResponseDto(false, new ArrayList<>());
            return ResponseEntity.ok().body(responseDto);
        }
        String role = account.get().getRole();
        Boolean isClient = role.contains("client");
        IsClientResponseDto responseDto = new IsClientResponseDto(isClient, new ArrayList<>());
        return ResponseEntity.ok().body(responseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<IsClientResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        ArrayList<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String message = error.getDefaultMessage();
            errors.add(message);
        });
        IsClientResponseDto responseDto = new IsClientResponseDto(false, errors);
        return ResponseEntity.ok().body(responseDto);
    }
}

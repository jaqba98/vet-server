package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.dto.request.guard.IsVetRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.guard.IsVetResponseDto;
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
public class IsVetController {
    private final TokenService tokenService;

    private final AccountService accountService;

    @PostMapping("is-vet")
    public ResponseEntity<IsVetResponseDto> isVet(@Valid @RequestBody IsVetRequestDto requestDto) {
        String token = requestDto.getToken();
        String email = tokenService.decode(token);
        Optional<Account> account = accountService.findByEmail(email);
        if (account.isEmpty()) {
            IsVetResponseDto responseDto = new IsVetResponseDto(false, new ArrayList<>());
            return ResponseEntity.ok().body(responseDto);
        }
        String role = account.get().getRole();
        Boolean isVet = role != null && role.contains("vet");
        IsVetResponseDto responseDto = new IsVetResponseDto(isVet, new ArrayList<>());
        return ResponseEntity.ok().body(responseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<IsVetResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        ArrayList<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String message = error.getDefaultMessage();
            errors.add(message);
        });
        IsVetResponseDto responseDto = new IsVetResponseDto(false, errors);
        return ResponseEntity.ok().body(responseDto);
    }
}

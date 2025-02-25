package com.jakubolejarczyk.vet_server.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jakubolejarczyk.vet_server.model.Account;
import com.jakubolejarczyk.vet_server.service.database.AccountService;
import com.jakubolejarczyk.vet_server.dto.request.LoginRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.LoginResponseDto;
import com.jakubolejarczyk.vet_server.service.security.JWTService;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class LoginController {
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    @PostMapping("login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) throws NoSuchAlgorithmException {
        String email = request.getEmail();
        String password = request.getPassword();
        Optional<Account> account = accountService.getAccountByEmail(email);
        if (account.isPresent()) {
            String hashPassword = account.get().getPassword();
            if (passwordEncoder.matches(password, hashPassword)) {
                String token = jwtService.generateToken(email);
                LoginResponseDto response = new LoginResponseDto(true, token);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }
        LoginResponseDto response = new LoginResponseDto(false, "");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

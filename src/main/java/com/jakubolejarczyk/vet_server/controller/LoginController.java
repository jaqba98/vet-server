package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.dto.request.LoginRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.LoginResponseDto;
import com.jakubolejarczyk.vet_server.model.Account;
import com.jakubolejarczyk.vet_server.service.database.AccountService;
import com.jakubolejarczyk.vet_server.service.security.PasswordService;
import com.jakubolejarczyk.vet_server.service.security.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class LoginController {
    private final AccountService accountService;

    private final PasswordService passwordService;

    private final TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity<LoginResponseDto> loginPost(@RequestBody LoginRequestDto requestDto) {
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();
        Optional<Account> account = accountService.getAccountByEmail(email);
        if (account.isPresent()) {
            String encodedPassword = account.get().getPassword();
            if (passwordService.match(password, encodedPassword)) {
                String token = tokenService.generate(email);
                LoginResponseDto responseDto = LoginResponseDto.builder()
                        .success(true)
                        .token(token)
                        .build();
                return ResponseEntity.status(HttpStatus.OK).body(responseDto);
            }
        }
        LoginResponseDto responseDto = LoginResponseDto.builder()
                .success(false)
                .token("")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}

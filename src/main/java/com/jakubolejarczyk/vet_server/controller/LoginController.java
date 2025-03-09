package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.dto.request.login.LoginRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.login.LoginResponseDto;
import com.jakubolejarczyk.vet_server.model.Account;
import com.jakubolejarczyk.vet_server.service.database.AccountService;
import com.jakubolejarczyk.vet_server.service.security.PasswordService;
import com.jakubolejarczyk.vet_server.service.security.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class LoginController {
    private final AccountService accountService;

    private final PasswordService passwordService;

    private final TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto) {
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();
        Optional<Account> account = accountService.findByEmail(email);
        ArrayList<String> errors = new ArrayList<>();
        if (account.isEmpty()) {
            errors.add("Incorrect email address or password!");
            LoginResponseDto responseDto = new LoginResponseDto(false, errors, "");
            return ResponseEntity.ok().body(responseDto);
        }
        String encodedPassword = account.get().getPassword();
        if (!passwordService.match(password, encodedPassword)) {
            errors.add("Incorrect email address or password!");
            LoginResponseDto responseDto = new LoginResponseDto(false, errors, "");
            return ResponseEntity.ok().body(responseDto);
        }
        String token = tokenService.generate(email);
        LoginResponseDto responseDto = new LoginResponseDto(true, errors, token);
        return ResponseEntity.ok().body(responseDto);
    }
}

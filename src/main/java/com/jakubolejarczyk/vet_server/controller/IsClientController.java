package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.dto.request.IsClientRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.IsClientResponseDto;
import com.jakubolejarczyk.vet_server.model.Account;
import com.jakubolejarczyk.vet_server.service.database.AccountService;
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
public class IsClientController {
    private final TokenService jwt;

    private final AccountService accountService;

    @PostMapping("is-client")
    public ResponseEntity<IsClientResponseDto> auth(@RequestBody IsClientRequestDto requestDto) {
        String token = requestDto.getToken();
        String email = jwt.decode(token);
        Optional<Account> account = accountService.getAccountByEmail(email);
        if (account.isPresent()) {
            String role = account.get().getRole();
            Boolean isVet = role.equals("client");
            IsClientResponseDto responseDto = IsClientResponseDto.builder().success(isVet).build();
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
        IsClientResponseDto responseDto = IsClientResponseDto.builder().success(false).build();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}

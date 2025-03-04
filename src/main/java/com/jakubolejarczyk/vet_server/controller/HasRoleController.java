package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.dto.request.HasRoleRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.HasRoleResponseDto;
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
public class HasRoleController {
    private final TokenService tokenService;

    private final AccountService accountService;

    @PostMapping("has-role")
    public ResponseEntity<HasRoleResponseDto> hasRole(@RequestBody HasRoleRequestDto requestDto) {
        String token = requestDto.getToken();
        String email = tokenService.decode(token);
        Optional<Account> account = accountService.getAccountByEmail(email);
        if (account.isPresent()) {
            String role = account.get().getRole();
            Boolean hasRole = role != null;
            HasRoleResponseDto responseDto = HasRoleResponseDto.builder()
                    .success(hasRole)
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
        HasRoleResponseDto responseDto = HasRoleResponseDto.builder()
                .success(false)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}

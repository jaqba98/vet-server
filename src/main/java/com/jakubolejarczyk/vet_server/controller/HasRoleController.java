package com.jakubolejarczyk.vet_server.controller;

import java.util.Optional;
import com.jakubolejarczyk.vet_server.model.Account;
import com.jakubolejarczyk.vet_server.service.database.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jakubolejarczyk.vet_server.dto.request.HasRoleRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.HasRoleResponseDto;
import com.jakubolejarczyk.vet_server.service.security.JWTService;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class HasRoleController {
    private final JWTService jwt;

    private final AccountService accountService;

    @PostMapping("has-role")
    public ResponseEntity<HasRoleResponseDto> auth(@RequestBody HasRoleRequestDto requestDto) {
        String token = requestDto.getToken();
        String email = jwt.decodeToken(token);
        Optional<Account> account = accountService.getAccountByEmail(email);
        if (account.isPresent()) {
            String role = account.get().getRole();
            Boolean hasRole = role != null;
            HasRoleResponseDto responseDto = new HasRoleResponseDto(hasRole);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
        HasRoleResponseDto responseDto = new HasRoleResponseDto(false);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}

package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.dto.request.IsVetRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.IsVetResponseDto;
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
public class IsVetController {
    private final TokenService jwt;

    private final AccountService accountService;

    @PostMapping("is-vet")
    public ResponseEntity<IsVetResponseDto> auth(@RequestBody IsVetRequestDto requestDto) {
        String token = requestDto.getToken();
        String email = jwt.decode(token);
        Optional<Account> account = accountService.getAccountByEmail(email);
        if (account.isPresent()) {
            String role = account.get().getRole();
            Boolean isVet = role.equals("vet");
            IsVetResponseDto responseDto = IsVetResponseDto.builder().success(isVet).build();
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
        IsVetResponseDto responseDto = IsVetResponseDto.builder().success(false).build();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}

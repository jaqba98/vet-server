package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
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
public class GetAccountController {
    private final AccountService accountService;

    private final TokenService tokenService;

    @PostMapping("get-account")
    public ResponseEntity<GetAccountResponseDto> getAccountPost(@RequestBody GetAccountRequestDto requestDto) {
        String token = requestDto.getToken();
        String email = tokenService.decode(token);
        Optional<Account> account = accountService.findByEmail(email);
        if (account.isPresent()) {
            String firstName = account.get().getFirstName();
            String lastName = account.get().getLastName();
            GetAccountResponseDto responseDto = GetAccountResponseDto.builder()
                    .success(true)
                    .firstName(firstName)
                    .lastName(lastName)
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
        GetAccountResponseDto responseDto = GetAccountResponseDto.builder()
                .success(false)
                .firstName("")
                .lastName("")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}

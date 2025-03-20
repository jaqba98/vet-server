package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
import com.jakubolejarczyk.vet_server.service.security.TokenService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetAccountByTokenStep {
    private final TokenService tokenService;
    private final AccountService accountService;

    public Optional<Account> runStep(String token) {
        val email = tokenService.decode(token);
        return accountService.findByEmail(email);
    }
}

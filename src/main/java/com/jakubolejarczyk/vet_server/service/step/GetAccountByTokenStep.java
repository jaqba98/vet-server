package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
import com.jakubolejarczyk.vet_server.service.model.StepResponse;
import com.jakubolejarczyk.vet_server.service.security.TokenService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAccountByTokenStep {
    private final TokenService tokenService;
    private final AccountService accountService;

    public StepResponse<Account> runStep(String token) {
        val email = tokenService.decode(token);
        val account = accountService.findByEmail(email);
        if (account.isPresent()) {
            return StepResponse.<Account>builder()
                    .success(true)
                    .data(account.get())
                    .build();
        }
        return StepResponse.<Account>builder()
                .success(false)
                .data(Account.builder().build())
                .build();
    }
}

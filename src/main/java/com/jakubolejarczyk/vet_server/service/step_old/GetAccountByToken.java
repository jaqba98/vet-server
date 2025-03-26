package com.jakubolejarczyk.vet_server.service.step_old;

import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import com.jakubolejarczyk.vet_server.service.security.TokenService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAccountByToken {
    private final TokenService tokenService;
    private final AccountService accountService;

    public StepModel<Account> runStep(ResponseStep responseStep, String token) {
        try {
            val email = tokenService.decode(token);
            val account = accountService.findByEmail(email);
            if (account.isPresent()) {
                return StepModel.<Account>builder()
                        .error(false)
                        .data(account.get())
                        .build();
            }
            responseStep.addMessage("Token is invalid!");
            return StepModel.<Account>builder()
                    .error(true)
                    .data(Account.builder().build())
                    .build();
        } catch (Exception e) {
            responseStep.addMessage("Token is invalid!");
            return StepModel.<Account>builder()
                    .error(true)
                    .data(Account.builder().build())
                    .build();
        }
    }
}

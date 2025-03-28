package com.jakubolejarczyk.vet_server.service.step.get;

import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import com.jakubolejarczyk.vet_server.service.model.StepOutput;
import com.jakubolejarczyk.vet_server.service.security.TokenService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAccountByTokenStep implements StepModel<String, Account> {
    private final TokenService tokenService;
    private final AccountService accountService;

    @Override
    public StepOutput<Account> runStep(String token) {
        try {
            val email = tokenService.decode(token);
            val account = accountService.findByEmail(email);
            if (account.isPresent()) {
                return StepOutput.<Account>builder()
                        .success(true)
                        .output(account.get())
                        .build();
            }
            return StepOutput.<Account>builder()
                    .success(false)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

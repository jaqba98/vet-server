package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
import com.jakubolejarczyk.vet_server.service.input.GetAccountByTokenInput;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import com.jakubolejarczyk.vet_server.service.output.StepOutput;
import com.jakubolejarczyk.vet_server.service.security.TokenService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAccountByTokenStep implements StepModel<GetAccountByTokenInput, Account> {
    private final TokenService tokenService;
    private final AccountService accountService;

    @Override
    public StepOutput<Account> runStep(GetAccountByTokenInput input) {
        try {
            val token = input.token();
            val email = tokenService.decode(token);
            val account = accountService.findByEmail(email);
            if (account.isPresent()) {
                return StepOutput.<Account>builder()
                        .success(true)
                        .data(account.get())
                        .build();
            }
            return StepOutput.<Account>builder()
                    .success(false)
                    .message("Failed to get account using token!")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

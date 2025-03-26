package com.jakubolejarczyk.vet_server.service.step_old;

import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
import com.jakubolejarczyk.vet_server.service.security.PasswordService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAccountStep {
    private final AccountService accountService;
    private final PasswordService passwordService;

    public void runStep(String email, String password, String firstName, String lastName) {
        val hashPassword = passwordService.encode(password);
        val account = Account.builder()
                .email(email)
                .password(hashPassword)
                .firstName(firstName)
                .lastName(lastName)
                .build();
        accountService.create(account);
    }
}

package com.jakubolejarczyk.vet_server.service.step.create;

import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import com.jakubolejarczyk.vet_server.service.security.PasswordService;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAccountStep implements StepModel {
    private final AccountService accountService;
    private final PasswordService passwordService;

    @Override
    public void runStep(StepStore stepStore) {
        val email = (String) stepStore.getItem("email");
        val accountByEmail = accountService.findByEmail(email);
        if (accountByEmail.isPresent()) {
            stepStore.setSuccess(false);
            return;
        }
        val password = (String) stepStore.getItem("password");
        val firstName = (String) stepStore.getItem("firstName");
        val lastName = (String) stepStore.getItem("lastName");
        val hashPassword = passwordService.encode(password);
        val account = Account.builder()
            .email(email)
            .password(hashPassword)
            .firstName(firstName)
            .lastName(lastName)
            .isArchived(false)
            .build();
        val newAccount = accountService.create(account);
        stepStore.setSuccess(true);
    }
}

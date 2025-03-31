package com.jakubolejarczyk.vet_server.step.create;

import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.security.PasswordService;
import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
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
        if (stepStore.hasNotItem("email")) throw new Error("The email is required!");
        if (stepStore.hasNotItem("password")) throw new Error("The password is required!");
        if (stepStore.hasNotItem("firstName")) throw new Error("The firstName is required!");
        if (stepStore.hasNotItem("lastName")) throw new Error("The lastName is required!");
        val email = stepStore.getItem("email", String.class);
        val password = stepStore.getItem("password", String.class);
        val firstName = stepStore.getItem("firstName", String.class);
        val lastName = stepStore.getItem("lastName", String.class);
        val hashPassword = passwordService.encode(password);
        val account = Account.builder()
                .isArchived(false)
                .email(email)
                .password(hashPassword)
                .firstName(firstName)
                .lastName(lastName)
                .role(null)
                .pictureUrl(null)
                .build();
        accountService.create(account);
        stepStore.addMessage("Registration was successful!");
    }
}

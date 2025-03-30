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
        if (stepStore.hasNotItem("email")) {
            throw new Error("The email is required in the CreateAccountStep step");
        }
        if (stepStore.hasNotItem("password")) {
            throw new Error("The password is required in the CreateAccountStep step");
        }
        if (stepStore.hasNotItem("firstName")) {
            throw new Error("The firstName is required in the CreateAccountStep step");
        }
        if (stepStore.hasNotItem("lastName")) {
            throw new Error("The lastName is required in the CreateAccountStep step");
        }
        val email = (String) stepStore.getItem("email");
        val password = (String) stepStore.getItem("password");
        val firstName = (String) stepStore.getItem("firstName");
        val lastName = (String) stepStore.getItem("lastName");
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

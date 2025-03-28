package com.jakubolejarczyk.vet_server.service.step.create;

import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
import com.jakubolejarczyk.vet_server.service.input.create.CreateAccountInput;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import com.jakubolejarczyk.vet_server.service.model.StepOutput;
import com.jakubolejarczyk.vet_server.service.output.create.CreateAccountOutput;
import com.jakubolejarczyk.vet_server.service.security.PasswordService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAccountStep implements StepModel<CreateAccountInput, CreateAccountOutput> {
    private final AccountService accountService;
    private final PasswordService passwordService;

    @Override
    public StepOutput<CreateAccountOutput> runStep(CreateAccountInput input) {
        try {
            val email = input.email();
            val accountByEmail = accountService.findByEmail(email);
            if (accountByEmail.isPresent()) {
                val output = new CreateAccountOutput(Account.builder().build());
                return new StepOutput<>(false, "The email address is already taken!", output);
            }
            val password = input.password();
            val firstName = input.firstName();
            val lastName = input.lastName();
            val hashPassword = passwordService.encode(password);
            val account = Account.builder()
                    .email(input.email())
                    .password(hashPassword)
                    .firstName(firstName)
                    .lastName(lastName)
                    .isArchived(false)
                    .build();
            val newAccount = accountService.create(account);
            val output = new CreateAccountOutput(newAccount);
            return new StepOutput<>(false, "The account has been created successfully!", output);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

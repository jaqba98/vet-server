package com.jakubolejarczyk.vet_server.step.get;

import com.jakubolejarczyk.vet_server.security.PasswordService;
import com.jakubolejarczyk.vet_server.security.TokenService;
import com.jakubolejarczyk.vet_server.service.independent.AccountService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetTokenByLoginDetailsStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final AccountService accountService;
    private final PasswordService passwordService;
    private final TokenService tokenService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("email")) throw new Error("The email is required!");
        if (stepStore.hasNotItem("password")) throw new Error("The password is required!");
        val email = stepStore.getItem("email", String.class);
        val account = accountService.findByEmail(email);
        if (account.isPresent()) {
            val password = stepStore.getItem("password", String.class);
            val encodedPassword = account.get().getPassword();
            if (passwordService.match(password, encodedPassword)) {
                val token = tokenService.generate(email);
                stepStore.setItem("token", token);
            }
            return;
        }
        stepStore.setSuccess(false);
    }
}

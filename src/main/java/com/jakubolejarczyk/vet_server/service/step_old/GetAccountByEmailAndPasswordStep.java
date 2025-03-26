package com.jakubolejarczyk.vet_server.service.step_old;

import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import com.jakubolejarczyk.vet_server.service.security.PasswordService;
import com.jakubolejarczyk.vet_server.service.security.TokenService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAccountByEmailAndPasswordStep {
    private final AccountService accountService;
    private final PasswordService passwordService;
    private final TokenService tokenService;

    public StepModel<String> runStep(ResponseStep responseStep, String email, String password) {
        val account = accountService.findByEmail(email);
        if (account.isEmpty()) {
            responseStep.addMessage("Incorrect email or password!");
            return StepModel.<String>builder()
                    .error(true)
                    .data("")
                    .build();
        }
        val encodedPassword = account.get().getPassword();
        if (passwordService.match(password, encodedPassword)) {
            String token = tokenService.generate(email);
            responseStep.addMessage("You have logged in successfully!");
            return StepModel.<String>builder()
                    .error(false)
                    .data(token)
                    .build();
        }
        responseStep.addMessage("Incorrect email or password!");
        return StepModel.<String>builder()
                .error(true)
                .data("")
                .build();
    }
}

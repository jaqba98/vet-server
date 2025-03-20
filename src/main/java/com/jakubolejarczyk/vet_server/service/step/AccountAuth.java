package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
import com.jakubolejarczyk.vet_server.service.model.StepResponse;
import com.jakubolejarczyk.vet_server.service.security.PasswordService;
import com.jakubolejarczyk.vet_server.service.security.TokenService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountAuth {
    private final AccountService accountService;
    private final PasswordService passwordService;
    private final TokenService tokenService;

    public StepResponse<String> runStep(String email, String password) {
        val account = accountService.findByEmail(email);
        if (account.isEmpty()) {
            return StepResponse.<String>builder()
                    .success(false)
                    .data("")
                    .build();
        }
        val encodedPassword = account.get().getPassword();
        if (passwordService.match(password, encodedPassword)) {
            String token = tokenService.generate(email);
            return StepResponse.<String>builder()
                    .success(true)
                    .data(token)
                    .build();
        }
        return StepResponse.<String>builder()
                .success(false)
                .data("")
                .build();
    }
}

package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import com.jakubolejarczyk.vet_server.service.model.StepResponse;
import com.jakubolejarczyk.vet_server.service.record.LoginDetailsRecord;
import com.jakubolejarczyk.vet_server.service.security.PasswordService;
import com.jakubolejarczyk.vet_server.service.security.TokenService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetTokenByLoginDetailsStep implements StepModel<LoginDetailsRecord, String> {
    private final AccountService accountService;
    private final PasswordService passwordService;
    private final TokenService tokenService;

    @Override
    public StepResponse<String> runStep(LoginDetailsRecord data) {
        val email = data.email();
        val account = accountService.findByEmail(email);
        if (account.isPresent()) {
            val password = data.password();
            val encodedPassword = account.get().getPassword();
            if (passwordService.match(password, encodedPassword)) {
                val token = tokenService.generate(email);
                return StepResponse.<String>builder()
                        .success(true)
                        .message("You have logged in successfully!")
                        .data(token)
                        .build();
            }
        }
        return StepResponse.<String>builder()
                .success(false)
                .message("Incorrect email or password!")
                .data("")
                .build();
    }
}

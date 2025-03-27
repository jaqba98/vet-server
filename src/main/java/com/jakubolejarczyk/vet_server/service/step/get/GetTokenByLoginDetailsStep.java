package com.jakubolejarczyk.vet_server.service.step.get;

import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import com.jakubolejarczyk.vet_server.service.output.StepOutput;
import com.jakubolejarczyk.vet_server.service.input.get.GetTokenByLoginDetailsInput;
import com.jakubolejarczyk.vet_server.service.security.PasswordService;
import com.jakubolejarczyk.vet_server.service.security.TokenService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetTokenByLoginDetailsStep implements StepModel<GetTokenByLoginDetailsInput, String> {
    private final AccountService accountService;
    private final PasswordService passwordService;
    private final TokenService tokenService;

    @Override
    public StepOutput<String> runStep(GetTokenByLoginDetailsInput input) {
        try {
            val account = accountService.findByEmail(input.email());
            if (account.isPresent()) {
                val encodedPassword = account.get().getPassword();
                if (passwordService.match(input.password(), encodedPassword)) {
                    val token = tokenService.generate(input.email());
                    return StepOutput.<String>builder()
                            .success(true)
                            .data(token)
                            .build();
                }
            }
            return StepOutput.<String>builder()
                    .success(false)
                    .message("Incorrect email or password!")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

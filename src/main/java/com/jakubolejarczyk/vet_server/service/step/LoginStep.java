package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
import com.jakubolejarczyk.vet_server.service.security.PasswordService;
import com.jakubolejarczyk.vet_server.service.security.TokenService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginStep {
    private final AccountService accountService;
    private final PasswordService passwordService;
    private final TokenService tokenService;

    public String runStep(String email, String password) {
        val account = accountService.findByEmail(email);
        if (account.isPresent()) {
            val encodedPassword = account.get().getPassword();
            if (passwordService.match(password, encodedPassword)) {
                return tokenService.generate(email);
            }
        }
        return "";
    }
}

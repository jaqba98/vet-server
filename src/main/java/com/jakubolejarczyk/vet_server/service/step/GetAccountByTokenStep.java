package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.dto.response.ResponseDataDto;
import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
import com.jakubolejarczyk.vet_server.service.security.TokenService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class GetAccountByTokenStep<TData> {
    private final TokenService tokenService;
    private final AccountService accountService;

    public ResponseDataDto<Account> runStep(String token) {
        val messages = new ArrayList<String>();
        val email = tokenService.decode(token);
        val account = accountService.findByEmail(email);
        if (account.isEmpty()) {
            messages.add("Account by given email does not exist!");
            return new ResponseDataDto<>(false, messages, Account.builder().build());
        }
        return new ResponseDataDto<>(true, messages, account.get());
    }
}

package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
import com.jakubolejarczyk.vet_server.service.security.TokenService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GetAccountByTokenStep<TData> {
    private final TokenService tokenService;
    private final AccountService accountService;

    public Optional<Account> getAccount(String token) {
        val email = tokenService.decode(token);
        return accountService.findByEmail(email);
    }

    public ResponseEntity<ResponseDto<TData>> buildErrorResponse(TData data) {
        val messages = new ArrayList<String>();
        messages.add("Account by given email does not exist!");
        val responseDto = new ResponseDto<>(false, messages, data);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}

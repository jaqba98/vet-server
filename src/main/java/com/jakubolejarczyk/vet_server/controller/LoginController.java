package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.dto.request.controller.LoginRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
import com.jakubolejarczyk.vet_server.service.security.PasswordService;
import com.jakubolejarczyk.vet_server.service.security.TokenService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class LoginController {
    private final AccountService accountService;

    private final PasswordService passwordService;

    private final TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity<ResponseDto<String>> login(@RequestBody LoginRequestDto requestDto) {
        val email = requestDto.getEmail();
        val password = requestDto.getPassword();
        val account = accountService.findByEmail(email);
        val errors = new ArrayList<String>();
        if (account.isEmpty()) {
            errors.add("Incorrect email or password!");
            val responseDto = new ResponseDto<>(false, errors, "");
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
        val encodedPassword = account.get().getPassword();
        if (!passwordService.match(password, encodedPassword)) {
            errors.add("Incorrect email or password!");
            val responseDto = new ResponseDto<>(false, errors, "");
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
        String token = tokenService.generate(email);
        val responseDto = new ResponseDto<>(true, errors, token);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}

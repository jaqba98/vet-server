package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.dto.request.controller.LoginRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDataDto;
import com.jakubolejarczyk.vet_server.service.step.AccountAuthStep;
import com.jakubolejarczyk.vet_server.service.step.ResponseStep;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class LoginController {
    private final AccountAuthStep accountAuthStep;
    private final ResponseStep responseStep;

    @PostMapping("login")
    public ResponseEntity<ResponseDataDto<String>> login(@RequestBody LoginRequestDto requestDto) {
        // Account auth
        val email = requestDto.getEmail();
        val password = requestDto.getPassword();
        val auth = accountAuthStep.runStep(email, password);
        if (!auth.getSuccess()) {
            responseStep.addMessage("Incorrect email or password!");
        }
        // Response
        return responseStep.getStep(auth.getSuccess(), auth.getData());
    }
}

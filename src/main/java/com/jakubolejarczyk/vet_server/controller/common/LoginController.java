package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.dto.request.controller.LoginRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDataDto;
import com.jakubolejarczyk.vet_server.service.step.AccountAuthStep;
import com.jakubolejarczyk.vet_server.service.step.ResponseStep;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class LoginController {
    private final AccountAuthStep accountAuthStep;
    private final ObjectFactory<ResponseStep> responseStep;

    @PostMapping("login")
    public ResponseEntity<ResponseDataDto<String>> login(@RequestBody LoginRequestDto requestDto) {
        // Clean response
        responseStep.getObject().getRidOfMessages();
        // Account auth
        val email = requestDto.getEmail();
        val password = requestDto.getPassword();
        val auth = accountAuthStep.runStep(email, password);
        if (!auth.getSuccess()) {
            responseStep.getObject().addMessage("Incorrect email or password!");
        }
        // Response
        responseStep.getObject().addMessage("You have logged in successfully!");
        return responseStep.getObject().getStep(auth.getSuccess(), auth.getData());
    }
}

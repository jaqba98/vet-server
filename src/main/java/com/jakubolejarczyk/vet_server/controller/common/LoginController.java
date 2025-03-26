package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.dto.request.controller.LoginRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDataDto;
import com.jakubolejarczyk.vet_server.service.step.GetAccountByEmailAndPasswordStep;
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
    private final GetAccountByEmailAndPasswordStep getAccountByEmailAndPasswordStep;
    private final ObjectFactory<ResponseStep> responseStep;

    @PostMapping("login")
    public ResponseEntity<ResponseDataDto<String>> login(@RequestBody LoginRequestDto requestDto) {
        // Init
        val responseStep = this.responseStep.getObject();
        responseStep.getRidOfMessages();
        val email = requestDto.getEmail();
        val password = requestDto.getPassword();
        // Get Account By Email And Password Step
        val accountResponse = getAccountByEmailAndPasswordStep.runStep(responseStep, email, password);
        if (accountResponse.getError()) return responseStep.getStep(false, "");
        // Return response
        val token = accountResponse.getData();
        return responseStep.getStep(true, token);
    }
}

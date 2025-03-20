package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.dto.request.controller.LoginRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDataDto;
import com.jakubolejarczyk.vet_server.service.step.LoginStep;
import com.jakubolejarczyk.vet_server.service.step.ResponseStep;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class LoginController {
    private final LoginStep loginStep;
    private final ResponseStep responseStep;

    @PostMapping("login")
    public ResponseEntity<ResponseDataDto<String>> login(@RequestBody LoginRequestDto requestDto) {
        val email = requestDto.getEmail();
        val password = requestDto.getPassword();
        val token = loginStep.runStep(email, password);
        if (token.isEmpty()) {
            responseStep.addMessage("Incorrect email or password!");
            return responseStep.getStep(false, "");
        }
        return responseStep.getStep(true, token);
    }
}

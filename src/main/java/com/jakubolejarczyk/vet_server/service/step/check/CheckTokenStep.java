package com.jakubolejarczyk.vet_server.service.step.check;

import com.jakubolejarczyk.vet_server.service.input.check.CheckTokenInput;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import com.jakubolejarczyk.vet_server.service.model.StepOutput;
import com.jakubolejarczyk.vet_server.service.output.check.CheckTokenOutput;
import com.jakubolejarczyk.vet_server.service.security.TokenService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckTokenStep implements StepModel<CheckTokenInput, CheckTokenOutput> {
    private final TokenService tokenService;

    @Override
    public StepOutput<CheckTokenOutput> runStep(CheckTokenInput input) {
        try {
            val token = input.token();
            val success = tokenService.verify(token);
            val message = success ? "The token is valid" : "The token is invalid";
            val output = new CheckTokenOutput();
            return new StepOutput<>(success, message, output);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

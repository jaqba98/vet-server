package com.jakubolejarczyk.vet_server.service.step.check;

import com.jakubolejarczyk.vet_server.service.model.StepModel;
import com.jakubolejarczyk.vet_server.service.output.StepOutput;
import com.jakubolejarczyk.vet_server.service.security.TokenService;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckTokenStep implements StepModel<String, Null> {
    private final TokenService tokenService;

    @Override
    public StepOutput<Null> runStep(String token) {
        try {
            return StepOutput.<Null>builder()
                    .success(tokenService.verify(token))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

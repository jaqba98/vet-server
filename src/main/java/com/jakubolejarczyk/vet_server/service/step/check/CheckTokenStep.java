package com.jakubolejarczyk.vet_server.service.step.check;

import com.jakubolejarczyk.vet_server.service.model.StepModel;
import com.jakubolejarczyk.vet_server.service.model.StepOutput;
import com.jakubolejarczyk.vet_server.service.security.TokenService;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckTokenStep implements StepModel<String, Null> {
    private final TokenService tokenService;

    @Override
    public StepOutput<Null> runStep(String token) {
        try {
            val success = tokenService.verify(token);
            return StepOutput.<Null>builder()
                    .success(success)
                    .output(null)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

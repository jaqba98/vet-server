package com.jakubolejarczyk.vet_server.step.check;

import com.jakubolejarczyk.vet_server.security.TokenService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckTokenStepRunner implements StepRunnerModel {
    private final TokenService tokenService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("token")) throw new Error("The token is required!");
        val token = stepStore.getItem("token", String.class);
        val validToken = tokenService.verify(token);
        if (validToken) {
            return;
        }
        stepStore.setSuccess(false);
        stepStore.addMessage("The token is invalid!");
    }
}

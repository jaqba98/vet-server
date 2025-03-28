package com.jakubolejarczyk.vet_server.step.check;

import com.jakubolejarczyk.vet_server.security.TokenService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckTokenStep implements StepModel {
    private final TokenService tokenService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("token")) {
            throw new Error("CheckTokenStep requires a token in the store!");
        }
        val token = (String) stepStore.getItem("token");
        val success = tokenService.verify(token);
        val message = success ? "The token is valid!" : "The token is invalid!";
        stepStore.setSuccess(success);
        stepStore.addMessage(message);
    }
}

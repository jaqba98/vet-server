package com.jakubolejarczyk.vet_server.step.response.logic;

import com.jakubolejarczyk.vet_server.dto.data.logic.LoginData;
import com.jakubolejarczyk.vet_server.dto.metadata.logic.LoginMetadata;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginResponseStep implements StepRunnerModel<LoginData, LoginMetadata> {
    @Override
    public void runStep(StepStore<LoginData, LoginMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            if (stepStore.hasNotItem("token")) throw new Error("The token is required!");
            val token = stepStore.getItem("token", String.class);
            val data = new LoginData(token);
            val metadata = new LoginMetadata();
            stepStore.addMessage("You have logged in successfully!");
            stepStore.setData(data);
            stepStore.setMetadata(metadata);
        }
    }
}

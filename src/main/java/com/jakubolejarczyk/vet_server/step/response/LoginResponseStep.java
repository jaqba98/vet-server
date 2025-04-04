package com.jakubolejarczyk.vet_server.step.response;

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
        if (stepStore.hasItem("token")) {
            val token = stepStore.getItem("token", String.class);
            val data = LoginData.builder().token(token).build();
            val metadata = LoginMetadata.builder().build();
            stepStore.setData(data);
            stepStore.setMetadata(metadata);
            stepStore.addMessage("You have logged in successfully!");
            return;
        }
        stepStore.addMessage("Incorrect login or password!");
    }
}

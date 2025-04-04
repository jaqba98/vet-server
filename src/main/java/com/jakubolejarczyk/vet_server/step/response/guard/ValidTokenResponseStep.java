package com.jakubolejarczyk.vet_server.step.response.guard;

import com.jakubolejarczyk.vet_server.dto.data.guard.GuardData;
import com.jakubolejarczyk.vet_server.dto.metadata.guard.GuardMetadata;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ValidTokenResponseStep implements StepRunnerModel<GuardData, GuardMetadata> {
    @Override
    public void runStep(StepStore<GuardData, GuardMetadata> stepStore) {
        val message = stepStore.getSuccess() ? "Token is valid!" : "Token is invalid!";
        val data = GuardData.builder().build();
        val metadata = GuardMetadata.builder().build();
        stepStore.addMessage(message);
        stepStore.setData(data);
        stepStore.setMetadata(metadata);
    }
}

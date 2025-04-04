package com.jakubolejarczyk.vet_server.step.response.guard;

import com.jakubolejarczyk.vet_server.dto.data.guard.ValidTokenData;
import com.jakubolejarczyk.vet_server.dto.metadata.guard.ValidTokenMetadata;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ValidTokenResponseStep implements StepRunnerModel<ValidTokenData, ValidTokenMetadata> {
    @Override
    public void runStep(StepStore<ValidTokenData, ValidTokenMetadata> stepStore) {
        val message = stepStore.getSuccess() ? "Token is valid!" : "Token is invalid!";
        val data = ValidTokenData.builder().build();
        val metadata = ValidTokenMetadata.builder().build();
        stepStore.addMessage(message);
        stepStore.setData(data);
        stepStore.setMetadata(metadata);
    }
}

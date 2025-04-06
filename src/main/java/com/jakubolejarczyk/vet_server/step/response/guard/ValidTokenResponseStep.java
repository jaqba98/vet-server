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
        if (stepStore.getSuccess()) {
            val data = new ValidTokenData();
            val metadata = new ValidTokenMetadata();
            stepStore.addMessage("Token is valid");
            stepStore.setData(data);
            stepStore.setMetadata(metadata);
        }
    }
}

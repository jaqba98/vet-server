package com.jakubolejarczyk.vet_server.step.response.logic;

import com.jakubolejarczyk.vet_server.dto.data.logic.RegistrationData;
import com.jakubolejarczyk.vet_server.dto.metadata.logic.RegistrationMetadata;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationResponseStep implements StepRunnerModel<RegistrationData, RegistrationMetadata> {
    @Override
    public void runStep(StepStore<RegistrationData, RegistrationMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            val data = RegistrationData.builder().build();
            val metadata = RegistrationMetadata.builder().build();
            stepStore.setData(data);
            stepStore.setMetadata(metadata);
            stepStore.addMessage("Registration was successful!");
        }
    }
}

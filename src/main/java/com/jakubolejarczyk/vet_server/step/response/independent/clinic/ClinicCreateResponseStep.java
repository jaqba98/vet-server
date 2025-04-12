package com.jakubolejarczyk.vet_server.step.response.independent.clinic;

import com.jakubolejarczyk.vet_server.dto.data.independent.ClinicData;
import com.jakubolejarczyk.vet_server.dto.metadata.independent.ClinicMetadata;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClinicCreateResponseStep implements StepRunnerModel<ClinicData, ClinicMetadata> {
    @Override
    public void runStep(StepStore<ClinicData, ClinicMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            stepStore.addMessage("A new clinic was created correctly!");
        }
    }
}

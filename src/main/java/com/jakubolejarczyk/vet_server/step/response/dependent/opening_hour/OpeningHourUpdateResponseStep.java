package com.jakubolejarczyk.vet_server.step.response.dependent.opening_hour;

import com.jakubolejarczyk.vet_server.dto.data.independent.OpeningHourData;
import com.jakubolejarczyk.vet_server.dto.metadata.independent.OpeningHourMetadata;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OpeningHourUpdateResponseStep implements StepRunnerModel<OpeningHourData, OpeningHourMetadata> {
    @Override
    public void runStep(StepStore<OpeningHourData, OpeningHourMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            stepStore.addMessage("Opening hours were updated correctly!");
        }
    }
}

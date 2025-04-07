package com.jakubolejarczyk.vet_server.step.response.dependent.medication;

import com.jakubolejarczyk.vet_server.dto.data.dependent.MedicationData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.MedicationMetadata;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MedicationDeleteResponseStep implements StepRunnerModel<MedicationData, MedicationMetadata> {
    @Override
    public void runStep(StepStore<MedicationData, MedicationMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            stepStore.addMessage("A new medication was deleted correctly!");
        }
    }
}

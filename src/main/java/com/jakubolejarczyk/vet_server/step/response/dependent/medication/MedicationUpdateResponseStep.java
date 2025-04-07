package com.jakubolejarczyk.vet_server.step.response.dependent.medication;

import com.jakubolejarczyk.vet_server.dto.data.dependent.MedicationData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.MedicationMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.Medication;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class MedicationUpdateResponseStep implements StepRunnerModel<MedicationData, MedicationMetadata> {
    @Override
    public void runStep(StepStore<MedicationData, MedicationMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            if (stepStore.hasNotItem("medicationData")) throw new Error("The medicationData is required!");
            val medicationData = stepStore.getItem("medicationData", Medication.class);
            val data = new MedicationData(Collections.singletonList(medicationData));
            stepStore.addMessage("Medication was updated correctly!");
            stepStore.setData(data);
        } else {
            if (stepStore.hasNotItem("medicationRequest")) throw new Error("The medicationRequest is required!");
            val medicationRequest = stepStore.getItem("medicationRequest", Medication.class);
            val data = new MedicationData(Collections.singletonList(medicationRequest));
            stepStore.setData(data);
        }
    }
}

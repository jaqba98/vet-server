package com.jakubolejarczyk.vet_server.step.response.dependent.medication;

import com.jakubolejarczyk.vet_server.dto.metadata.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.dto.data.dependent.MedicationData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.MedicationMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.Medication;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MedicationReadResponseStep implements StepRunnerModel<MedicationData, MedicationMetadata> {
    @Override
    public void runStep(StepStore<MedicationData, MedicationMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            if (stepStore.hasNotItem("medicationsData")) throw new Error("The medicationsData is required!");
            if (stepStore.hasNotItem("clinicId")) throw new Error("The clinicId is required!");
            val medicationsData = stepStore.getItemAsArray("medicationsData", Medication.class);
            val clinicId = stepStore.getItem("clinicId", BaseMetadata.class);
            val data = new MedicationData(medicationsData);
            val metadata = new MedicationMetadata(clinicId);
            stepStore.addMessage("Medications were read correctly!");
            stepStore.setData(data);
            stepStore.setMetadata(metadata);
        }
    }
}

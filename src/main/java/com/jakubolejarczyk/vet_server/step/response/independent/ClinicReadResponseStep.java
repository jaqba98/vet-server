package com.jakubolejarczyk.vet_server.step.response.independent;

import com.jakubolejarczyk.vet_server.dto.data.dependent.ClinicData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.ClinicMetadata;
import com.jakubolejarczyk.vet_server.model.independent.Clinic;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClinicReadResponseStep implements StepRunnerModel<ClinicData, ClinicMetadata> {
    @Override
    public void runStep(StepStore<ClinicData, ClinicMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            if (stepStore.hasNotItem("clinicsData")) throw new Error("The clinicsData is required!");
            val clinicsData = stepStore.getItemAsArray("clinicsData", Clinic.class);
            val data = new ClinicData(clinicsData);
            val metadata = new ClinicMetadata();
            stepStore.addMessage("Clinics were read correctly");
            stepStore.setData(data);
            stepStore.setMetadata(metadata);
        }
    }
}

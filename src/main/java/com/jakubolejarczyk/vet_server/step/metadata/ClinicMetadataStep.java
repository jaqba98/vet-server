package com.jakubolejarczyk.vet_server.step.metadata;

import com.jakubolejarczyk.vet_server.dto.data.independent.ClinicData;
import com.jakubolejarczyk.vet_server.dto.metadata.independent.ClinicMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClinicMetadataStep implements StepRunnerModel<ClinicData, ClinicMetadata> {
    @Override
    public void runStep(StepStore<ClinicData, ClinicMetadata> stepStore) {
        if (stepStore.hasNotItem("employmentsData")) throw new Error("The employmentsData is required!");
        val employmentsData = stepStore.getItemAsArray("employmentsData", Employment.class);
        val myClinicIds = employmentsData.stream()
            .filter(Employment::getIsOwner)
            .map(Employment::getClinicId)
            .toList();
        stepStore.setItem("myClinicIds", myClinicIds);
    }
}

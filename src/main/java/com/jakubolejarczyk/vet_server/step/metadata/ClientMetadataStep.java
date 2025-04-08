package com.jakubolejarczyk.vet_server.step.metadata;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.dto.data.dependent.ClientData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.ClientMetadata;
import com.jakubolejarczyk.vet_server.model.independent.Clinic;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientMetadataStep implements StepRunnerModel<ClientData, ClientMetadata> {
    @Override
    public void runStep(StepStore<ClientData, ClientMetadata> stepStore) {
        if (stepStore.hasNotItem("clinicsData")) throw new Error("The clinicsData is required!");
        val clinicsData = stepStore.getItemAsArray("clinicsData", Clinic.class);
        val clinicId = new BaseMetadata();
        clinicsData.forEach((clinic) -> clinicId.addValue(clinic.getId(), clinic.getFullName()));
        stepStore.setItem("clinicId", clinicId);
    }
}

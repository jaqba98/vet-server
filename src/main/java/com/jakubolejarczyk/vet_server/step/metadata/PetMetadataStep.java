package com.jakubolejarczyk.vet_server.step.metadata;

import com.jakubolejarczyk.vet_server.dto.metadata.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.dto.data.dependent.PetData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.PetMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.Client;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PetMetadataStep implements StepRunnerModel<PetData, PetMetadata> {
    @Override
    public void runStep(StepStore<PetData, PetMetadata> stepStore) {
        if (stepStore.hasNotItem("clientsData")) throw new Error("The clientsData is required!");
        val clientsData = stepStore.getItemAsArray("clientsData", Client.class);
        val clientId = new BaseMetadata();
        clientsData.forEach((client) -> {
            val fullName = client.getFirstName() + " " + client.getLastName();
            clientId.addValue(client.getId(), fullName);
        });
        stepStore.setItem("clientId", clientId);
    }
}

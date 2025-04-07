package com.jakubolejarczyk.vet_server.step.response.dependent.pet;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.dto.data.dependent.PetData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.PetMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.Pet;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PetReadResponseStep implements StepRunnerModel<PetData, PetMetadata> {
    @Override
    public void runStep(StepStore<PetData, PetMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            if (stepStore.hasNotItem("petsData")) throw new Error("The petsData is required!");
            if (stepStore.hasNotItem("clientId")) throw new Error("The clientId is required!");
            val petsData = stepStore.getItemAsArray("petsData", Pet.class);
            val clientId = stepStore.getItem("clientId", BaseMetadata.class);
            val data = new PetData(petsData);
            val metaData = new PetMetadata(clientId);
            stepStore.addMessage("The pet has been read successfully!");
            stepStore.setData(data);
            stepStore.setMetadata(metaData);
        }
    }
}

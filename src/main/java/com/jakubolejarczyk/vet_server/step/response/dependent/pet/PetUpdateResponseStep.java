package com.jakubolejarczyk.vet_server.step.response.dependent.pet;

import com.jakubolejarczyk.vet_server.dto.data.dependent.PetData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.PetMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.Pet;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class PetUpdateResponseStep implements StepRunnerModel<PetData, PetMetadata> {
    @Override
    public void runStep(StepStore<PetData, PetMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            if (stepStore.hasNotItem("petData")) throw new Error("The petData is required!");
            val petData = stepStore.getItem("petData", Pet.class);
            val data = new PetData(Collections.singletonList(petData));
            stepStore.addMessage("Pet was updated correctly!");
            stepStore.setData(data);
        } else {
            if (stepStore.hasNotItem("petRequest")) throw new Error("The petRequest is required!");
            val petRequest = stepStore.getItem("petRequest", Pet.class);
            val data = new PetData(Collections.singletonList(petRequest));
            stepStore.setData(data);
        }
    }
}

package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.service.dependent.PetService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdatePetIsArchivedStepRunner implements StepRunnerModel {
    private final PetService petService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("petIds")) throw new Error("The petIds is required!");
        val petIds = stepStore.getItemAsArray("petIds", Long.class);
        petService.deleteAllById(petIds);
    }
}

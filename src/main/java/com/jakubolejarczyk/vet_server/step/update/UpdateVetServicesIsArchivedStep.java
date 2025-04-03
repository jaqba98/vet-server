package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.service.dependent.VetService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateVetServicesIsArchivedStep implements StepModel {
    private final VetService vetService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("vetServicesIds")) throw new Error("The vetServicesIds is required!");
        val vetServicesIds = stepStore.getItemAsArray("vetServicesIds", Long.class);
        vetService.deleteAllById(vetServicesIds);
    }
}

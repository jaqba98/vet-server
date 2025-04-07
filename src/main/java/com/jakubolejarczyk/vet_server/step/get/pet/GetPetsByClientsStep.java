package com.jakubolejarczyk.vet_server.step.get.pet;

import com.jakubolejarczyk.vet_server.model.dependent.Client;
import com.jakubolejarczyk.vet_server.service.dependent.PetService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetPetsByClientsStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final PetService petService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("clientsData")) throw new Error("The clientsData is required!");
        val clientsData = stepStore.getItemAsArray("clientsData", Client.class);
        val clientsIds = clientsData.stream()
            .map(Client::getId)
            .toList();
        val petsData = petService.findAllByClientIdIn(clientsIds);
        stepStore.setItem("petsData", petsData);
    }
}

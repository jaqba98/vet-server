package com.jakubolejarczyk.vet_server.step.get;

import com.jakubolejarczyk.vet_server.model.dependent.Client;
import com.jakubolejarczyk.vet_server.service.dependent.PetService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetPetsByClientsStep implements StepModel {
    private final PetService petService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("clients")) throw new Error("The clients is required!");
        val clients = stepStore.getItemAsArray("clients", Client.class);
        val clientsIds = clients.stream()
                .map(Client::getId)
                .toList();
        val pets = petService.findAllByClientIds(clientsIds);
        stepStore.setItem("pets", pets);
    }
}

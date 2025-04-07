package com.jakubolejarczyk.vet_server.step.get.client;

import com.jakubolejarczyk.vet_server.model.dependent.Pet;
import com.jakubolejarczyk.vet_server.service.dependent.ClientService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetClientByPetRequestStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final ClientService clientService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("petRequest")) throw new Error("The petRequest is required!");
        val petRequest = stepStore.getItem("petRequest", Pet.class);
        val clientData = clientService.findById(petRequest.getClientId());
        if (clientData.isPresent()) {
            stepStore.setItem("clientData", clientData.get());
            return;
        }
        stepStore.setSuccess(false);
        stepStore.addMessage("The client does not exists.");
    }
}

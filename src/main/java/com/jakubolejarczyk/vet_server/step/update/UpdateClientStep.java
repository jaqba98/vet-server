package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.model.dependent.Client;
import com.jakubolejarczyk.vet_server.service.dependent.ClientService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateClientStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final ClientService clientService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("clientRequest")) throw new Error("The clientRequest is required!");
        val clientRequest = stepStore.getItem("clientRequest", Client.class);
        val clientId = clientRequest.getId();
        val currentClient = clientService.findById(clientId);
        if (currentClient.isEmpty()) {
            stepStore.setSuccess(false);
            stepStore.addMessage("Failed to update client!");
            return;
        }
        currentClient.get().setEmail(clientRequest.getEmail());
        currentClient.get().setPhoneNumber(clientRequest.getPhoneNumber());
        currentClient.get().setFirstName(clientRequest.getFirstName());
        currentClient.get().setLastName(clientRequest.getLastName());
        currentClient.get().setClinicId(clientRequest.getClinicId());
        val clientData = clientService.save(currentClient.get());
        stepStore.setItem("clientData", clientData);
    }
}

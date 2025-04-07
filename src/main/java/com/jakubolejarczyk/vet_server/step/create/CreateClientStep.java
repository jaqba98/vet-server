package com.jakubolejarczyk.vet_server.step.create;

import com.jakubolejarczyk.vet_server.model.dependent.Client;
import com.jakubolejarczyk.vet_server.service.dependent.ClientService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateClientStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final ClientService clientService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("clientRequest")) throw new Error("The clientRequest is required!");
        val clientRequest = stepStore.getItem("clientRequest", Client.class);
        val clinicByEmail = clientService.findByEmail(clientRequest.getEmail());
        if (clinicByEmail.isPresent()) {
            stepStore.setSuccess(false);
            stepStore.addMessage("The email of the client is already taken!");
            return;
        }
        val client = Client.builder()
            .email(clientRequest.getEmail())
            .phoneNumber(clientRequest.getPhoneNumber())
            .firstName(clientRequest.getFirstName())
            .lastName(clientRequest.getLastName())
            .clinicId(clientRequest.getClinicId())
            .build();
        val clientData = clientService.save(client);
        stepStore.setItem("clientData", clientData);
    }
}

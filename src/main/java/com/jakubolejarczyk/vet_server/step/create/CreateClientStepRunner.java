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
public class CreateClientStepRunner implements StepRunnerModel {
    private final ClientService clientService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("requestClient")) throw new Error("The requestClient is required!");
        if (stepStore.hasNotItem("clinicId")) throw new Error("The clinicId is required!");
        val requestClient = stepStore.getItem("requestClient", Client.class);
        val clinicId = stepStore.getItem("clinicId", Long.class);
        val client = Client.builder()
                .email(requestClient.getEmail())
                .phoneNumber(requestClient.getPhoneNumber())
                .firstName(requestClient.getFirstName())
                .lastName(requestClient.getLastName())
                .clinicId(clinicId)
                .build();
        clientService.save(client);
    }
}

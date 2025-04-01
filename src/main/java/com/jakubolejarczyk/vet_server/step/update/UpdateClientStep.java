package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.model.dependent.Client;
import com.jakubolejarczyk.vet_server.service.dependent.ClientService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateClientStep implements StepModel {
    private final ClientService clientService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("requestClient")) throw new Error("The requestClient is required!");
        val requestClient = stepStore.getItem("requestClient", Client.class);
        val clientId = requestClient.getId();
        val currentClient = clientService.findById(clientId);
        if (currentClient.isPresent()) {
            val newClient = Client.builder()
                    .id(currentClient.get().getId())
                    .isArchived(currentClient.get().getIsArchived())
                    .email(requestClient.getEmail())
                    .phoneNumber(requestClient.getPhoneNumber())
                    .firstName(requestClient.getFirstName())
                    .lastName(requestClient.getLastName())
                    .clinicId(requestClient.getClinicId())
                    .build();
            val client = clientService.create(newClient);
            stepStore.setItem("client", client);
            return;
        }
        stepStore.setSuccess(false);
        stepStore.addMessage("Failed to update a client.");
    }
}

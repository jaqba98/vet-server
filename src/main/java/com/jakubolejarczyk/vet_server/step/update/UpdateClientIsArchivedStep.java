package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.service.dependent.ClientService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateClientIsArchivedStep implements StepModel {
    private final ClientService clientService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("clientIds")) throw new Error("The clientIds is required!");
        val clientIds = stepStore.getItemAsArray("clientIds", Long.class);
        clientService.updateIsArchived(clientIds, true);
    }
}

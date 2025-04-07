package com.jakubolejarczyk.vet_server.step.delete;

import com.jakubolejarczyk.vet_server.service.dependent.ClientService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteClientsStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final ClientService clientService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("clientIds")) throw new Error("The clientIds is required!");
        val clientIds = stepStore.getItemAsArray("clientIds", Long.class);
        clientService.deleteAllById(clientIds);
    }
}

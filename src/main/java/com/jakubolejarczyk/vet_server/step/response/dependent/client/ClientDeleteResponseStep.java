package com.jakubolejarczyk.vet_server.step.response.dependent.client;

import com.jakubolejarczyk.vet_server.dto.data.dependent.ClientData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.ClientMetadata;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientDeleteResponseStep implements StepRunnerModel<ClientData, ClientMetadata> {
    @Override
    public void runStep(StepStore<ClientData, ClientMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            stepStore.addMessage("Client was deleted correctly!");
        }
    }
}


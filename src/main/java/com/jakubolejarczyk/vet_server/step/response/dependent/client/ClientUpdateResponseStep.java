package com.jakubolejarczyk.vet_server.step.response.dependent.client;

import com.jakubolejarczyk.vet_server.dto.data.dependent.ClientData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.ClientMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.Client;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class ClientUpdateResponseStep implements StepRunnerModel<ClientData, ClientMetadata> {
    @Override
    public void runStep(StepStore<ClientData, ClientMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            if (stepStore.hasNotItem("clientData")) throw new Error("The clientData is required!");
            val clientData = stepStore.getItem("clientData", Client.class);
            val data = new ClientData(Collections.singletonList(clientData));
            stepStore.addMessage("Client were updated correctly!");
            stepStore.setData(data);
        }
    }
}


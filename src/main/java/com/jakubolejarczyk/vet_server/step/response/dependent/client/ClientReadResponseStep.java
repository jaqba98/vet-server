package com.jakubolejarczyk.vet_server.step.response.dependent.client;

import com.jakubolejarczyk.vet_server.dto.metadata.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.dto.data.dependent.ClientData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.ClientMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.Client;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientReadResponseStep implements StepRunnerModel<ClientData, ClientMetadata> {
    @Override
    public void runStep(StepStore<ClientData, ClientMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            if (stepStore.hasNotItem("clientsData")) throw new Error("The clientsData is required!");
            if (stepStore.hasNotItem("clinicId")) throw new Error("The clinicId is required!");
            val clientsData = stepStore.getItemAsArray("clientsData", Client.class);
            val clinicId = stepStore.getItem("clinicId", BaseMetadata.class);
            val data = new ClientData(clientsData);
            val metadata = new ClientMetadata(clinicId);
            stepStore.addMessage("Clients were read correctly!");
            stepStore.setData(data);
            stepStore.setMetadata(metadata);
        }
    }
}


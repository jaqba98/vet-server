package com.jakubolejarczyk.vet_server.step.get;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.service.dependent.ClientService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetClientsByEmploymentStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final ClientService clientService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("employments")) throw new Error("The employments is required!");
        val employments = stepStore.getItemAsArray("employments", Employment.class);
        // Data
        val clinicsIds = employments.stream()
            .map(Employment::getClinicId)
            .toList();
        val clients = clientService.findAllByClinicIdIn(clinicsIds);
        stepStore.setItem("clients", clients);
        // MetaData
        val clientIdMetaData = new BaseMetadata();
        clients.forEach(client -> clientIdMetaData.addValue(client.getId(), client.getFullName()));
        stepStore.setItem("clientIdMetaData", clientIdMetaData);
    }
}

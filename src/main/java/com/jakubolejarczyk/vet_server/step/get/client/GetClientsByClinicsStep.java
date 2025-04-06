package com.jakubolejarczyk.vet_server.step.get.client;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.model.independent.Clinic;
import com.jakubolejarczyk.vet_server.service.dependent.ClientService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetClientsByClinicsStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final ClientService clientService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("clinicsData")) throw new Error("The clinicsData is required!");
        val clinicsData = stepStore.getItemAsArray("clinicsData", Clinic.class);
        val clinicsIds = clinicsData.stream()
            .map(Clinic::getId)
            .toList();
        val clientsData = clientService.findAllByClinicIdIn(clinicsIds);
        // Data
        stepStore.setItem("clientsData", clientsData);
        // MetaData clinic id
        val clientsClinicIdMetaData = new BaseMetadata();
        clinicsData.forEach(clinic -> {
            clientsClinicIdMetaData.addValue(clinic.getId(), clinic.getFullName());
        });
        stepStore.setItem("clientsClinicIdMetaData", clientsClinicIdMetaData);
    }
}

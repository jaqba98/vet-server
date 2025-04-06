package com.jakubolejarczyk.vet_server.step.get;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.service.independent.ClinicService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetClinicsByEmploymentStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final ClinicService clinicService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("employments")) throw new Error("The employments is required!");
        val employments = stepStore.getItemAsArray("employments", Employment.class);
        // Data
        val clinicsIds = employments.stream()
            .map(Employment::getClinicId)
            .toList();
        val clinics = clinicService.findAllById(clinicsIds);
        stepStore.setItem("clinics", clinics);
        // MetaData
        val clinicIdMetaData = new BaseMetadata();
        clinics.forEach(client -> clinicIdMetaData.addValue(client.getId(), client.getFullName()));
        stepStore.setItem("clinicIdMetaData", clinicIdMetaData);
    }
}

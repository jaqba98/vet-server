package com.jakubolejarczyk.vet_server.step.get.clinic;

import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.service.independent.ClinicService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetClinicsByEmploymentsStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final ClinicService clinicService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("employmentsData")) throw new Error("The employmentsData is required!");
        val employmentsData = stepStore.getItemAsArray("employmentsData", Employment.class);
        val clinicIds = employmentsData.stream()
            .map(Employment::getClinicId)
            .distinct()
            .toList();
        val clinicsData = clinicService.findAllById(clinicIds);
        // Data
        stepStore.setItem("clinicsData", clinicsData);
    }
}

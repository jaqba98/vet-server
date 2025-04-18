package com.jakubolejarczyk.vet_server.step.get.opening_hour;

import com.jakubolejarczyk.vet_server.model.independent.Clinic;
import com.jakubolejarczyk.vet_server.service.dependent.OpeningHourService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetOpeningHoursByClinicsStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final OpeningHourService openingHourService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("clinicsData")) throw new Error("The clinicsData is required!");
        val clinicsData = stepStore.getItemAsArray("clinicsData", Clinic.class);
        val clinicIds = clinicsData.stream()
            .map(Clinic::getId)
            .distinct()
            .toList();
        val openingHoursData = openingHourService.findAllByClinicIdIn(clinicIds);
        stepStore.setItem("openingHoursData", openingHoursData);
    }
}

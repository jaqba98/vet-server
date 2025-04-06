package com.jakubolejarczyk.vet_server.step.delete;

import com.jakubolejarczyk.vet_server.model.dependent.OpeningHour;
import com.jakubolejarczyk.vet_server.model.independent.Clinic;
import com.jakubolejarczyk.vet_server.service.dependent.OpeningHourService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteOpeningHoursByClinicsStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final OpeningHourService openingHourService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("clinicsData")) throw new Error("The clinicsData is required!");
        val clinicsData = stepStore.getItemAsArray("clinicsData", Clinic.class);
        val clinicIds = clinicsData.stream()
            .map(Clinic::getId)
            .toList();
        val openingHoursIds = openingHourService.findAllByClinicIdIn(clinicIds).stream()
            .map(OpeningHour::getId)
            .toList();
        openingHourService.deleteAllById(openingHoursIds);
    }
}

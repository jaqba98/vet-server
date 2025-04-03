package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.model.independent.OpeningHour;
import com.jakubolejarczyk.vet_server.service.independent.OpeningHourService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateOpeningHoursIsArchivedStep implements StepModel {
    private final OpeningHourService openingHourService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("employments")) throw new Error("The employments is required!");
        val employments = stepStore.getItemAsArray("employments", Employment.class);
        val clinicsIds = employments.stream()
                .map(Employment::getClinicId)
                .toList();
        val openingHoursIds = openingHourService.findAllById(clinicsIds).stream()
                .map(OpeningHour::getId)
                .toList();
        openingHourService.deleteAllById(openingHoursIds);
    }
}

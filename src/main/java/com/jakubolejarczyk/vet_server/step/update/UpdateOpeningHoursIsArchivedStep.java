package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.model.independent.OpeningHours;
import com.jakubolejarczyk.vet_server.service.independent.OpeningHoursService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateOpeningHoursIsArchivedStep implements StepModel {
    private final OpeningHoursService openingHoursService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("employments")) throw new Error("The employments is required!");
        val employments = stepStore.getItemAsArray("employments", Employment.class);
        val clinicsIds = employments.stream()
                .map(Employment::getClinicId)
                .toList();
        val openingHoursIds = openingHoursService.findAllById(clinicsIds).stream()
                .map(OpeningHours::getId)
                .toList();
        openingHoursService.updateIsArchived(openingHoursIds, true);
    }
}

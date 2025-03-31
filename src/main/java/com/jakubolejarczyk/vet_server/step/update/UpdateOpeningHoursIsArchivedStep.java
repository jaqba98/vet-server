package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.service.crud.dependent.ClinicService;
import com.jakubolejarczyk.vet_server.service.crud.independent.OpeningHoursService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateOpeningHoursIsArchivedStep implements StepModel {
    private final ClinicService clinicService;
    private final OpeningHoursService openingHoursService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("employments")) throw new Error("The employments is required!");
        val clinicIds = stepStore.getItemAsArray("employments", Employment.class).stream()
                .map(Employment::getClinicId)
                .toList();
        val openingHoursIds = clinicService.findAllById(clinicIds).stream()
                .map(Clinic::getOpeningHoursId)
                .toList();
        openingHoursService.updateIsArchived(openingHoursIds, true);
        stepStore.addMessage("The opening hours have been archived successfully!");
    }
}

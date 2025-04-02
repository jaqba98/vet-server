package com.jakubolejarczyk.vet_server.step.get;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.service.dependent.ClinicService;
import com.jakubolejarczyk.vet_server.service.independent.OpeningHoursService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetOpeningHoursForClinicIdsStep implements StepModel {
    private final ClinicService clinicService;
    private final OpeningHoursService openingHoursService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("clinicIds")) throw new Error("The clinicIds is required!");
        val clinicIds = stepStore.getItemAsArray("clinicIds", Long.class);
        val openingHoursIds = clinicService.findAllById(clinicIds).stream()
                .map(Clinic::getOpeningHourId)
                .toList();
        val openingHours = openingHoursService.findAllById(openingHoursIds);
        stepStore.setItem("openingHours", openingHours);
    }
}

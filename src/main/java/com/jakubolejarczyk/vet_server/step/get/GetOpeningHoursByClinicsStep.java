package com.jakubolejarczyk.vet_server.step.get;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.service.independent.OpeningHoursService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetOpeningHoursByClinicsStep implements StepModel {
    private final OpeningHoursService openingHoursService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("clinics")) throw new Error("The clinics is required!");
        val clinics = stepStore.getItemAsArray("clinics", Clinic.class);
        val openingHoursId = clinics.stream()
                .map(Clinic::getOpeningHourId)
                .toList();
        val openingHours = openingHoursService.findAllById(openingHoursId);
        stepStore.setItem("openingHours", openingHours);
    }
}

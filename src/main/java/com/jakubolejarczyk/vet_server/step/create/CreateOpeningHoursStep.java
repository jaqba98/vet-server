package com.jakubolejarczyk.vet_server.step.create;

import com.jakubolejarczyk.vet_server.model.independent.OpeningHour;
import com.jakubolejarczyk.vet_server.service.independent.OpeningHourService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateOpeningHoursStep implements StepModel {
    private final OpeningHourService openingHourService;

    @Override
    public void runStep(StepStore stepStore) {
        val newOpeningHours = OpeningHour.builder()
                .build();
        val openingHoursToSave = openingHourService.save(newOpeningHours);
        stepStore.setItem("openingHours", openingHoursToSave);
    }
}

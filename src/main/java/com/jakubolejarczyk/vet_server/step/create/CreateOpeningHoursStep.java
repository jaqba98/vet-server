package com.jakubolejarczyk.vet_server.step.create;

import com.jakubolejarczyk.vet_server.model.independent.OpeningHours;
import com.jakubolejarczyk.vet_server.service.crud.independent.OpeningHoursService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateOpeningHoursStep implements StepModel {
    private final OpeningHoursService openingHoursService;

    @Override
    public void runStep(StepStore stepStore) {
        val openingHours = OpeningHours.builder()
                .isArchived(false)
                .build();
        val newOpeningHours = openingHoursService.create(openingHours);
        stepStore.setItem("openingHours", newOpeningHours);
    }
}

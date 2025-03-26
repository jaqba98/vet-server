package com.jakubolejarczyk.vet_server.service.step_old;

import com.jakubolejarczyk.vet_server.model.independent.OpeningHours;
import com.jakubolejarczyk.vet_server.service.crud.independent.OpeningHoursService;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateOpeningHoursStep {
    private final OpeningHoursService openingHoursService;

    public StepModel<OpeningHours> runStep(ResponseStep responseStep, Boolean isArchived) {
        try {
            val openingHours = openingHoursService.create(isArchived);
            return StepModel.<OpeningHours>builder()
                    .error(false)
                    .data(openingHours)
                    .build();
        } catch (Exception e) {
            responseStep.addMessage("Failed to create opening hours!");
            return StepModel.<OpeningHours>builder()
                    .error(true)
                    .data(OpeningHours.builder().build())
                    .build();
        }
    }
}

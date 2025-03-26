package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.model.independent.OpeningHours;
import com.jakubolejarczyk.vet_server.service.crud.independent.OpeningHoursService;
import com.jakubolejarczyk.vet_server.service.model.StepResponse;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class GetOpeningHoursByIdsStep {
    private final OpeningHoursService openingHoursService;

    public StepResponse<ArrayList<OpeningHours>> runStep(ResponseStep responseStep, ArrayList<Long> openingHoursIds) {
        try {
            val openingHours = new ArrayList<>(openingHoursService.findAllByIds(openingHoursIds));
            return StepResponse.<ArrayList<OpeningHours>>builder()
                    .error(false)
                    .data(openingHours)
                    .build();
        } catch (Exception e) {
            responseStep.addMessage("Failed to get opening hours by IDs!");
            return StepResponse.<ArrayList<OpeningHours>>builder()
                    .error(true)
                    .data(new ArrayList<>())
                    .build();
        }
    }
}

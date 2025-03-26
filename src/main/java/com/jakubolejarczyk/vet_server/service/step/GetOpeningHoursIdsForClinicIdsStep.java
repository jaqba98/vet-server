package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.service.crud.dependent.ClinicService;
import com.jakubolejarczyk.vet_server.service.model.StepResponse;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetOpeningHoursIdsForClinicIdsStep {
    private final ClinicService clinicService;

    public StepResponse<ArrayList<Long>> runStep(ResponseStep responseStep, ArrayList<Long> clinicIds) {
        try {
            val openingHoursIds = clinicService.findAllByIds(clinicIds).stream()
                    .map(Clinic::getOpeningHoursId)
                    .collect(Collectors.toCollection(ArrayList::new));
            return StepResponse.<ArrayList<Long>>builder()
                    .error(false)
                    .data(openingHoursIds)
                    .build();
        } catch (Exception e) {
            responseStep.addMessage("Failed to get opening hours IDs for clinic IDs!");
            return StepResponse.<ArrayList<Long>>builder()
                    .error(true)
                    .data(new ArrayList<>())
                    .build();
        }
    }
}

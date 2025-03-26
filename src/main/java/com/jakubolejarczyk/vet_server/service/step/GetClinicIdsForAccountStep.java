package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.service.crud.dependent.EmploymentService;
import com.jakubolejarczyk.vet_server.service.model.StepResponse;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetClinicIdsForAccountStep {
    private final EmploymentService employmentService;

    public StepResponse<ArrayList<Long>> runStep(ResponseStep responseStep, Long accountId) {
        try {
            val clinicIds = employmentService.findAllByAccountId(accountId).stream()
                    .map(Employment::getClinicId)
                    .collect(Collectors.toCollection(ArrayList::new));
            return StepResponse.<ArrayList<Long>>builder()
                    .error(false)
                    .data(clinicIds)
                    .build();
        } catch (Exception e) {
            responseStep.addMessage("Failed to get clinic IDs for account!");
            return StepResponse.<ArrayList<Long>>builder()
                    .error(true)
                    .data(new ArrayList<>())
                    .build();
        }
    }
}

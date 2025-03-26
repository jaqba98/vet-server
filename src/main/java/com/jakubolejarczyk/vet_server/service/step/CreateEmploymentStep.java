package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.service.crud.dependent.EmploymentService;
import com.jakubolejarczyk.vet_server.service.model.StepResponse;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateEmploymentStep {
    private final EmploymentService employmentService;

    public StepResponse<Employment> runStep(ResponseStep responseStep, Boolean isOwner, Long accountId, Clinic clinic) {
        try {
            val employment = employmentService.create(Employment.builder()
                    .isOwner(isOwner)
                    .isArchived(clinic.getIsArchived())
                    .accountId(accountId)
                    .clinicId(clinic.getId())
                    .build());
            return StepResponse.<Employment>builder()
                    .error(false)
                    .data(employment)
                    .build();
        } catch (Exception e) {
            responseStep.addMessage("Failed to create employment!");
            return StepResponse.<Employment>builder()
                    .error(true)
                    .data(Employment.builder().build())
                    .build();
        }
    }
}

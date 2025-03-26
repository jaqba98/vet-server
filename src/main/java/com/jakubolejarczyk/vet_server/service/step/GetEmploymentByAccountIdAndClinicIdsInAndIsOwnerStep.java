package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.service.crud.dependent.EmploymentService;
import com.jakubolejarczyk.vet_server.service.model.StepResponse;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetEmploymentByAccountIdAndClinicIdsInAndIsOwnerStep {
    private final EmploymentService employmentService;

    public StepResponse<ArrayList<Employment>> runStep(ResponseStep responseStep, Long accountId, List<Long> clinicIds) {
        try {
            val employment = new ArrayList<>(
                    employmentService.findAllByAccountIdAndClinicIdsInAndIsOwner(accountId, clinicIds)
            );
            return StepResponse.<ArrayList<Employment>>builder()
                    .error(false)
                    .data(employment)
                    .build();
        } catch (Exception e) {
            responseStep.addMessage("Failed to get employment for owner account!");
            return StepResponse.<ArrayList<Employment>>builder()
                    .error(true)
                    .data(new ArrayList<>())
                    .build();
        }
    }
}

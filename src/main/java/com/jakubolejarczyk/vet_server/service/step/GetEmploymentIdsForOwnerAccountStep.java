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
public class GetEmploymentIdsForOwnerAccountStep {
    private final EmploymentService employmentService;

    public StepResponse<ArrayList<Long>> runStep(ResponseStep responseStep, Long accountId) {
        val employmentIds = employmentService.findAllByAccountIdAndIsOwner(accountId).stream()
                .map(Employment::getId)
                .collect(Collectors.toCollection(ArrayList::new));
        return StepResponse.<ArrayList<Long>>builder()
                .error(false)
                .data(employmentIds)
                .build();
    }
}

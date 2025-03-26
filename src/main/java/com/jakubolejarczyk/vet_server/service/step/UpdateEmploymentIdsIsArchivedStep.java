package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.service.crud.dependent.EmploymentService;
import com.jakubolejarczyk.vet_server.service.model.StepResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UpdateEmploymentIdsIsArchivedStep {
    private final EmploymentService employmentService;

    public StepResponse<Boolean> runStep(ResponseStep responseStep, List<Long> ids, Boolean isArchived) {
        employmentService.updateIsArchived(ids, isArchived);
        return StepResponse.<Boolean>builder()
                .error(false)
                .data(true)
                .build();
    }
}

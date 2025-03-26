package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.service.crud.independent.OpeningHoursService;
import com.jakubolejarczyk.vet_server.service.model.StepResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UpdateClinicIdsIsArchivedStep {
    private final OpeningHoursService openingHoursService;

    public StepResponse<Boolean> runStep(ResponseStep responseStep, List<Long> ids, Boolean isArchived) {
        openingHoursService.updateIsArchived(ids, isArchived);
        return StepResponse.<Boolean>builder()
                .error(false)
                .data(true)
                .build();
    }
}

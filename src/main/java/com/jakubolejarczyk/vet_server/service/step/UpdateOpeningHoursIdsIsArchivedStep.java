package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.service.crud.dependent.ClinicService;
import com.jakubolejarczyk.vet_server.service.model.StepResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UpdateOpeningHoursIdsIsArchivedStep {
    private final ClinicService clinicService;

    public StepResponse<Boolean> runStep(ResponseStep responseStep, List<Long> ids, Boolean isArchived) {
        clinicService.updateIsArchived(ids, isArchived);
        return StepResponse.<Boolean>builder()
                .error(false)
                .data(true)
                .build();
    }
}

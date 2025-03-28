package com.jakubolejarczyk.vet_server.service.step.get;

import com.jakubolejarczyk.vet_server.model.independent.OpeningHours;
import com.jakubolejarczyk.vet_server.service.crud.independent.OpeningHoursService;
import com.jakubolejarczyk.vet_server.service.input.get.GetOpeningHoursByIdsInput;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import com.jakubolejarczyk.vet_server.service.model.StepOutput;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetOpeningHoursByIdsStep implements StepModel<GetOpeningHoursByIdsInput, List<OpeningHours>> {
    private final OpeningHoursService openingHoursService;

    @Override
    public StepOutput<List<OpeningHours>> runStep(GetOpeningHoursByIdsInput input) {
        try {
            val openingHours = openingHoursService.findAllById(input.openingHoursIds());
            return StepOutput.<List<OpeningHours>>builder()
                    .success(true)
                    .data(openingHours)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

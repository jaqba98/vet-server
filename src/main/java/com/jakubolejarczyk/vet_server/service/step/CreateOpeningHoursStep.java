package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.model.independent.OpeningHours;
import com.jakubolejarczyk.vet_server.service.crud.independent.OpeningHoursService;
import com.jakubolejarczyk.vet_server.service.input.CreateOpeningHoursInput;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import com.jakubolejarczyk.vet_server.service.output.StepOutput;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateOpeningHoursStep implements StepModel<CreateOpeningHoursInput, OpeningHours> {
    private final OpeningHoursService openingHoursService;

    @Override
    public StepOutput<OpeningHours> runStep(CreateOpeningHoursInput input) {
        try {
            val openingHours = OpeningHours.builder()
                    .isArchived(false)
                    .build();
            val newOpeningHours = openingHoursService.create(openingHours);
            return StepOutput.<OpeningHours>builder()
                    .success(true)
                    .data(newOpeningHours)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

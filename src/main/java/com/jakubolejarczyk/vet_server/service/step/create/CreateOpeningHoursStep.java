package com.jakubolejarczyk.vet_server.service.step.create;

import com.jakubolejarczyk.vet_server.model.independent.OpeningHours;
import com.jakubolejarczyk.vet_server.service.crud.independent.OpeningHoursService;
import com.jakubolejarczyk.vet_server.service.input.create.CreateOpeningHoursInput;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import com.jakubolejarczyk.vet_server.service.model.StepOutput;
import com.jakubolejarczyk.vet_server.service.output.create.CreateOpeningHoursOutput;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateOpeningHoursStep implements StepModel<CreateOpeningHoursInput, CreateOpeningHoursOutput> {
    private final OpeningHoursService openingHoursService;

    @Override
    public StepOutput<CreateOpeningHoursOutput> runStep(CreateOpeningHoursInput input) {
        try {
            val openingHours = OpeningHours.builder()
                    .isArchived(false)
                    .build();
            val newOpeningHours = openingHoursService.create(openingHours);
            val output = new CreateOpeningHoursOutput(newOpeningHours);
            return new StepOutput<>(true, "The opening hours has been established", output);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

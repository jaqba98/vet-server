package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.dto.request.controller.OpeningHoursRequestDto;
import com.jakubolejarczyk.vet_server.model.independent.OpeningHours;
import com.jakubolejarczyk.vet_server.service.crud.independent.OpeningHoursService;
import com.jakubolejarczyk.vet_server.service.model.StepResponse;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateOpeningHoursStep {
    private final OpeningHoursService openingHoursService;

    public StepResponse<OpeningHours> runStep(ResponseStep responseStep, OpeningHoursRequestDto requestDto) {
        try {
            val openingHours = OpeningHours.builder()
                    .id(requestDto.getId())
                    .mondayFrom(requestDto.getMondayFrom())
                    .mondayTo(requestDto.getMondayTo())
                    .tuesdayFrom(requestDto.getTuesdayFrom())
                    .tuesdayTo(requestDto.getTuesdayTo())
                    .wednesdayFrom(requestDto.getWednesdayFrom())
                    .wednesdayTo(requestDto.getWednesdayTo())
                    .thursdayFrom(requestDto.getThursdayFrom())
                    .tuesdayTo(requestDto.getTuesdayTo())
                    .fridayFrom(requestDto.getFridayFrom())
                    .fridayTo(requestDto.getFridayTo())
                    .saturdayFrom(requestDto.getSaturdayFrom())
                    .saturdayTo(requestDto.getSaturdayTo())
                    .sundayFrom(requestDto.getSundayFrom())
                    .sundayTo(requestDto.getSundayTo())
                    .isArchived(requestDto.getIsArchived())
                    .build();
            openingHoursService.update(openingHours);
            return StepResponse.<OpeningHours>builder()
                    .error(false)
                    .data(openingHours)
                    .build();
        } catch (Exception e) {
            responseStep.addMessage("Failed to update opening hours!");
            return StepResponse.<OpeningHours>builder()
                    .error(true)
                    .data(OpeningHours.builder().build())
                    .build();
        }
    }
}

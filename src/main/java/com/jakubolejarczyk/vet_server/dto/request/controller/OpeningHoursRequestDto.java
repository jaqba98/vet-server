package com.jakubolejarczyk.vet_server.dto.request.controller;

import com.jakubolejarczyk.vet_server.domain.independent.OpeningHoursDomain;
import com.jakubolejarczyk.vet_server.dto.base.TokenRequestDto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class OpeningHoursRequestDto extends TokenRequestDto implements OpeningHoursDomain {
    private Long id;

    @NotNull(message = "Monday from is required!")
    private LocalTime mondayFrom;

    @NotNull(message = "Monday to is required!")
    private LocalTime mondayTo;

    @NotNull(message = "Tuesday from is required!")
    private LocalTime tuesdayFrom;

    @NotNull(message = "Tuesday to is required!")
    private LocalTime tuesdayTo;

    @NotNull(message = "Wednesday from is required!")
    private LocalTime wednesdayFrom;

    @NotNull(message = "Wednesday to is required!")
    private LocalTime wednesdayTo;

    @NotNull(message = "Thursday from is required!")
    private LocalTime thursdayFrom;

    @NotNull(message = "Thursday to is required!")
    private LocalTime thursdayTo;

    @NotNull(message = "Friday from is required!")
    private LocalTime fridayFrom;

    @NotNull(message = "Friday to is required!")
    private LocalTime fridayTo;

    @NotNull(message = "Saturday from is required!")
    private LocalTime saturdayFrom;

    @NotNull(message = "Saturday to is required!")
    private LocalTime saturdayTo;

    @NotNull(message = "Sunday from is required!")
    private LocalTime sundayFrom;

    @NotNull(message = "Sunday to is required!")
    private LocalTime sundayTo;

    @NotNull(message = "Is archived is required!")
    private Boolean isArchived;
}

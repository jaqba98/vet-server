package com.jakubolejarczyk.vet_server.dto.request.dependent;

import com.jakubolejarczyk.vet_server.domain.dependent.OpeningHourDomain;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class OpeningHourRequest extends TokenRequest implements OpeningHourDomain {
    @NotNull(message = "Id is required!")
    private Long id;

    private LocalTime mondayFrom;

    private LocalTime mondayTo;

    private LocalTime tuesdayFrom;

    private LocalTime tuesdayTo;

    private LocalTime wednesdayFrom;

    private LocalTime wednesdayTo;

    private LocalTime thursdayFrom;

    private LocalTime thursdayTo;

    private LocalTime fridayFrom;

    private LocalTime fridayTo;

    private LocalTime saturdayFrom;

    private LocalTime saturdayTo;

    private LocalTime sundayFrom;

    private LocalTime sundayTo;

    @NotNull(message = "Clinic id is required!")
    private Long clinicId;
}

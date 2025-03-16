package com.jakubolejarczyk.vet_server.model;

import com.jakubolejarczyk.vet_server.domain.OpeningHoursDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
public class OpeningHours extends OpeningHoursDomain {
    private Long id;

    private Time mondayFrom;

    private Time mondayTo;

    private Time tuesdayFrom;

    private Time tuesdayTo;

    private Time wednesdayFrom;

    private Time wednesdayTo;

    private Time thursdayFrom;

    private Time thursdayTo;

    private Time fridayFrom;

    private Time fridayTo;

    private Time saturdayFrom;

    private Time saturdayTo;

    private Time sundayFrom;

    private Time sundayTo;
}

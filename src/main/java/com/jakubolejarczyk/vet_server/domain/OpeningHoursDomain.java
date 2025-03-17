package com.jakubolejarczyk.vet_server.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
public class OpeningHoursDomain {
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

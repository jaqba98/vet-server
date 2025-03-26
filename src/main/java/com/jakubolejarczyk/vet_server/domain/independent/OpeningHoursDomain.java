package com.jakubolejarczyk.vet_server.domain.independent;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

import java.time.LocalTime;

public class OpeningHoursDomain extends BaseDomain {
    protected LocalTime mondayFrom;
    protected LocalTime mondayTo;
    protected LocalTime tuesdayFrom;
    protected LocalTime tuesdayTo;
    protected LocalTime wednesdayFrom;
    protected LocalTime wednesdayTo;
    protected LocalTime thursdayFrom;
    protected LocalTime thursdayTo;
    protected LocalTime fridayFrom;
    protected LocalTime fridayTo;
    protected LocalTime saturdayFrom;
    protected LocalTime saturdayTo;
    protected LocalTime sundayFrom;
    protected LocalTime sundayTo;
}

package com.jakubolejarczyk.vet_server.domain.independent;

import java.sql.Time;

public interface OpeningHoursDomain {
    Long getId();
    Time getMondayFrom();
    Time getMondayTo();
    Time getTuesdayFrom();
    Time getTuesdayTo();
    Time getWednesdayFrom();
    Time getWednesdayTo();
    Time getThursdayFrom();
    Time getThursdayTo();
    Time getFridayFrom();
    Time getFridayTo();
    Time getSaturdayFrom();
    Time getSaturdayTo();
    Time getSundayFrom();
    Time getSundayTo();
}

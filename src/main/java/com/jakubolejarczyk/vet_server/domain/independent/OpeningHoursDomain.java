package com.jakubolejarczyk.vet_server.domain.independent;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

import java.time.LocalTime;

public interface OpeningHoursDomain extends BaseDomain {
    LocalTime getMondayFrom();
    LocalTime getMondayTo();
    LocalTime getTuesdayFrom();
    LocalTime getTuesdayTo();
    LocalTime getWednesdayFrom();
    LocalTime getWednesdayTo();
    LocalTime getThursdayFrom();
    LocalTime getThursdayTo();
    LocalTime getFridayFrom();
    LocalTime getFridayTo();
    LocalTime getSaturdayFrom();
    LocalTime getSaturdayTo();
    LocalTime getSundayFrom();
    LocalTime getSundayTo();
}

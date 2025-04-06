package com.jakubolejarczyk.vet_server.domain.dependent;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

import java.time.LocalTime;

public interface OpeningHourDomain extends BaseDomain {
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
    Long getClinicId();
}

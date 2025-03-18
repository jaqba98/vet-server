package com.jakubolejarczyk.vet_server.domain;

import java.sql.Time;

public interface OpeningHoursDomain {
    Long getId();
    void setId(Long id);

    Time getMondayFrom();
    void setMondayFrom(Time mondayFrom);

    Time getMondayTo();
    void setMondayTo(Time mondayTo);

    Time getTuesdayFrom();
    void setTuesdayFrom(Time tuesdayFrom);

    Time getTuesdayTo();
    void setTuesdayTo(Time tuesdayTo);

    Time getWednesdayFrom();
    void setWednesdayFrom(Time wednesdayFrom);

    Time getWednesdayTo();
    void setWednesdayTo(Time wednesdayTo);

    Time getThursdayFrom();
    void setThursdayFrom(Time thursdayFrom);

    Time getThursdayTo();
    void setThursdayTo(Time thursdayTo);

    Time getFridayFrom();
    void setFridayFrom(Time fridayFrom);

    Time getFridayTo();
    void setFridayTo(Time fridayTo);

    Time getSaturdayFrom();
    void setSaturdayFrom(Time saturdayFrom);

    Time getSaturdayTo();
    void setSaturdayTo(Time saturdayTo);

    Time getSundayFrom();
    void setSundayFrom(Time sundayFrom);

    Time getSundayTo();
    void setSundayTo(Time sundayTo);
}

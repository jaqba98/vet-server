package com.jakubolejarczyk.vet_server.domain;

import java.sql.Time;

public abstract class OpeningHoursDomain {
    abstract Long getId();

    abstract Time getMondayFrom();

    abstract Time getMondayTo();

    abstract Time getTuesdayFrom();

    abstract Time getTuesdayTo();

    abstract Time getWednesdayFrom();

    abstract Time getWednesdayTo();

    abstract Time getThursdayFrom();

    abstract Time getThursdayTo();

    abstract Time getFridayFrom();

    abstract Time getFridayTo();

    abstract Time getSaturdayFrom();

    abstract Time getSaturdayTo();

    abstract Time getSundayFrom();

    abstract Time getSundayTo();
}

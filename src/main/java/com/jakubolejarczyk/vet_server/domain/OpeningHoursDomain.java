package com.jakubolejarczyk.vet_server.domain;

import java.sql.Time;

public abstract class OpeningHoursDomain {
    protected abstract Long getId();

    protected abstract Time getMondayFrom();

    protected abstract Time getMondayTo();

    protected abstract Time getTuesdayFrom();

    protected abstract Time getTuesdayTo();

    protected abstract Time getWednesdayFrom();

    protected abstract Time getWednesdayTo();

    protected abstract Time getThursdayFrom();

    protected abstract Time getThursdayTo();

    protected abstract Time getFridayFrom();

    protected abstract Time getFridayTo();

    protected abstract Time getSaturdayFrom();

    protected abstract Time getSaturdayTo();

    protected abstract Time getSundayFrom();

    protected abstract Time getSundayTo();
}

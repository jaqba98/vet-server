package com.jakubolejarczyk.vet_server.domain;

public abstract class ClinicDomain {
    protected abstract Long getId();

    protected abstract String getName();

    protected abstract String getStreet();

    protected abstract String getBuildingNumber();

    protected abstract String getApartmentNumber();

    protected abstract String getPostalCode();

    protected abstract String getCity();

    protected abstract String getProvince();

    protected abstract String getCountry();

    protected abstract String getEmail();

    protected abstract String getPhoneNumber();

    protected abstract Long getOpeningHoursId();
}

package com.jakubolejarczyk.vet_server.domain;

public abstract class ClinicDomain {
    abstract Long getId();

    abstract String getName();

    abstract String getStreet();

    abstract String getBuildingNumber();

    abstract String getApartmentNumber();

    abstract String getPostalCode();

    abstract String getCity();

    abstract String getProvince();

    abstract String getCountry();

    abstract String getEmail();

    abstract String getPhoneNumber();
}

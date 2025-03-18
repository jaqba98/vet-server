package com.jakubolejarczyk.vet_server.domain.dependent;

public interface ClinicDomain {
    Long getId();
    String getName();
    String getStreet();
    String getBuildingNumber();
    String getApartmentNumber();
    String getPostalCode();
    String getCity();
    String getProvince();
    String getCountry();
    String getEmail();
    String getPhoneNumber();
    Long getOpeningHoursId();
}

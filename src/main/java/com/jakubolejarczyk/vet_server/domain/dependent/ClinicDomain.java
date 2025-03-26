package com.jakubolejarczyk.vet_server.domain.dependent;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

public interface ClinicDomain extends BaseDomain {
    String getName();
    String getStreet();
    String getBuildingNumber();
    String getApartmentNumber();
    String gtPostalCode();
    String getCity();
    String getProvince();
    String getCountry();
    String getEmail();
    String getPhoneNumber();
    Long getOpeningHoursId();
}

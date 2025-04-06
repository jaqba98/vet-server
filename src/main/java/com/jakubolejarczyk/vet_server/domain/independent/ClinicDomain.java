package com.jakubolejarczyk.vet_server.domain.independent;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

public interface ClinicDomain extends BaseDomain {
    String getFullName();
    String getStreet();
    String getBuildingNumber();
    String getApartmentNumber();
    String getPostalCode();
    String getCity();
    String getProvince();
    String getCountry();
    String getEmail();
    String getPhoneNumber();
}

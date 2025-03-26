package com.jakubolejarczyk.vet_server.domain.dependent;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

public class ClinicDomain extends BaseDomain {
    protected String name;
    protected String street;
    protected String buildingNumber;
    protected String apartmentNumber;
    protected String postalCode;
    protected String city;
    protected String province;
    protected String country;
    protected String email;
    protected String phoneNumber;
    protected Long openingHoursId;
}

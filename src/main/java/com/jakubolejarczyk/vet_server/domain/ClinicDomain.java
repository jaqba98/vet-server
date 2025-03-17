package com.jakubolejarczyk.vet_server.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicDomain {
    private Long id;
    private String name;
    private String street;
    private String buildingNumber;
    private String apartmentNumber;
    private String postalCode;
    private String city;
    private String province;
    private String country;
    private String email;
    private String phoneNumber;
    private Long openingHoursId;
}

package com.jakubolejarczyk.vet_server.model;

import com.jakubolejarczyk.vet_server.domain.ClinicDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Clinic extends ClinicDomain {
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

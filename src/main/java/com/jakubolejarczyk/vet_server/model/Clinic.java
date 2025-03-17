package com.jakubolejarczyk.vet_server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Clinic extends ClinicDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 150)
    private String name;

    @Column(name = "street", nullable = false, length = 100)
    private String street;

    @Column(name = "building_number", nullable = false, length = 10)
    private String buildingNumber;

    @Column(name = "apartment_number", nullable = false, length = 10)
    private String apartmentNumber;

    @Column(name = "postal_code", nullable = false, length = 10)
    private String postalCode;

    @Column(name = "city", nullable = false, length = 80)
    private String city;

    @Column(name = "province", nullable = false, length = 80)
    private String province;

    @Column(name = "country", nullable = false, length = 56)
    private String country;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "opening_hours_id", nullable = false)
    private Long openingHoursId;
}

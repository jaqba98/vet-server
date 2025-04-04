package com.jakubolejarczyk.vet_server.model.dependent;

import com.jakubolejarczyk.vet_server.domain.dependent.ClinicDomain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Clinic implements ClinicDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, unique = true, length = 150)
    private String fullName;

    @Column(nullable = false, length = 100)
    private String street;

    @Column(name = "building_number", nullable = false, length = 10)
    private String buildingNumber;

    @Column(name = "apartment_number", length = 10)
    private String apartmentNumber;

    @Column(name = "postal_code", nullable = false, length = 10)
    private String postalCode;

    @Column(nullable = false, length = 80)
    private String city;

    @Column(nullable = false, length = 80)
    private String province;

    @Column(nullable = false, length = 56)
    private String country;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "opening_hour_id", nullable = false)
    private Long openingHourId;
}

package com.jakubolejarczyk.vet_server.service.input;

public record CreateClinicInput(
        String name,
        String street,
        String buildingNumber,
        String apartmentNumber,
        String postalCode,
        String city,
        String province,
        String country,
        String email,
        String phoneNumber,
        Long openingHoursId
) {
}

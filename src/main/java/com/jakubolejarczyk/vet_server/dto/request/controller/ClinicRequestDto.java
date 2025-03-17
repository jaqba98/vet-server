package com.jakubolejarczyk.vet_server.dto.request.controller;

import com.jakubolejarczyk.vet_server.domain.ClinicDomain;
import com.jakubolejarczyk.vet_server.validator.token.Token;
import com.jakubolejarczyk.vet_server.validator.unique.Unique;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicRequestDto extends ClinicDomain {
    @NotNull(message = "Token is requires!")
    @NotBlank(message = "Token cannot be empty!")
    @Token
    private String token;

    @NotNull(message = "Name is requires!")
    @NotBlank(message = "Name cannot be empty!")
    @Unique(message = "There is a clinic with the given name!", table = "clinic", column = "name")
    private String name;

    @NotNull(message = "Street is requires!")
    @NotBlank(message = "Street cannot be empty!")
    private String street;

    @NotNull(message = "Building number is requires!")
    @NotBlank(message = "Building number be empty!")
    private String buildingNumber;

    @NotNull(message = "Apartment Number is requires!")
    @NotBlank(message = "Apartment Number cannot be empty!")
    private String apartmentNumber;

    @NotNull(message = "Postal Code is requires!")
    @NotBlank(message = "Postal Code cannot be empty!")
    private String postalCode;

    @NotNull(message = "City is requires!")
    @NotBlank(message = "City cannot be empty!")
    private String city;

    @NotNull(message = "Province is requires!")
    @NotBlank(message = "Province cannot be empty!")
    private String province;

    @NotNull(message = "Country is requires!")
    @NotBlank(message = "Country cannot be empty!")
    private String country;

    @NotNull(message = "Email is requires!")
    @NotBlank(message = "Email cannot be empty!")
    private String email;

    @NotNull(message = "Phone number is requires!")
    @NotBlank(message = "Phone number cannot be empty!")
    private String phoneNumber;

    @NotNull(message = "Opening hours id is requires!")
    @NotBlank(message = "Opening hours id cannot be empty!")
    private Long openingHoursId;
}

package com.jakubolejarczyk.vet_server.dto.request.independent;

import com.jakubolejarczyk.vet_server.domain.independent.ClinicDomain;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicRequest extends TokenRequest implements ClinicDomain {
    @NotNull(message = "Id is required!")
    private Long id;

    @NotNull(message = "Full name is required!")
    @NotBlank(message = "Full name cannot be empty!")
    @Size(max = 150, message = "Full name cannot be longer than 150 characters!")
    private String fullName;

    @NotNull(message = "Street is required!")
    @NotBlank(message = "Street cannot be empty!")
    @Size(max = 100, message = "Street cannot be longer than 100 characters!")
    private String street;

    @NotNull(message = "Building number is required!")
    @NotBlank(message = "Building number cannot be empty!")
    @Size(max = 10, message = "Building number cannot be longer than 10 characters!")
    private String buildingNumber;

    @Size(max = 10, message = "Apartment number cannot be longer than 10 characters!")
    private String apartmentNumber;

    @NotNull(message = "Postal code is required!")
    @NotBlank(message = "Postal code cannot be empty!")
    @Size(max = 10, message = "Postal code cannot be longer than 10 characters!")
    private String postalCode;

    @NotNull(message = "City is required!")
    @NotBlank(message = "City cannot be empty!")
    @Size(max = 80, message = "City cannot be longer than 80 characters!")
    private String city;

    @NotNull(message = "Province is required!")
    @NotBlank(message = "Province cannot be empty!")
    @Size(max = 80, message = "Province cannot be longer than 80 characters!")
    private String province;

    @NotNull(message = "Country is required!")
    @NotBlank(message = "Country cannot be empty!")
    @Size(max = 56, message = "Country cannot be longer than 56 characters!")
    private String country;

    @NotNull(message = "Email is required!")
    @NotBlank(message = "Email cannot be empty!")
    @Size(max = 255, message = "Email cannot be longer than 255 characters!")
    private String email;

    @NotNull(message = "Phone number is required!")
    @NotBlank(message = "Phone number cannot be empty!")
    @Size(max = 20, message = "Phone number cannot be longer than 20 characters!")
    private String phoneNumber;
}

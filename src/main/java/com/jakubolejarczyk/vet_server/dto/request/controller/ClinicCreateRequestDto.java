//package com.jakubolejarczyk.vet_server.dto.request.controller;
//
//import com.jakubolejarczyk.vet_server.domain.dependent.ClinicDomain;
//import com.jakubolejarczyk.vet_server.dto.base.TokenRequestDto;
//import com.jakubolejarczyk.vet_server.validator.unique.Unique;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//public class ClinicCreateRequestDto extends TokenRequestDto implements ClinicDomain {
//    private Long id;
//
//    @NotNull(message = "Name is required!")
//    @NotBlank(message = "Name cannot be empty!")
//    @Size(max = 150, message = "Name cannot be longer than 150 characters!")
//    @Unique(message = "There is a clinic with the given name!", table = "clinic", column = "name")
//    private String name;
//
//    @NotNull(message = "Street is required!")
//    @NotBlank(message = "Street cannot be empty!")
//    @Size(max = 100, message = "Street cannot be longer than 100 characters!")
//    private String street;
//
//    @NotNull(message = "Building number is required!")
//    @NotBlank(message = "Building number cannot be empty!")
//    @Size(max = 10, message = "Building number cannot be longer than 10 characters!")
//    private String buildingNumber;
//
//    @NotNull(message = "Apartment number is required!")
//    @Size(max = 10, message = "Apartment number cannot be longer than 10 characters!")
//    private String apartmentNumber;
//
//    @NotNull(message = "Postal code is required!")
//    @NotBlank(message = "Postal code cannot be empty!")
//    @Size(max = 10, message = "Postal code cannot be longer than 10 characters!")
//    private String postalCode;
//
//    @NotNull(message = "City is required!")
//    @NotBlank(message = "City cannot be empty!")
//    @Size(max = 80, message = "City cannot be longer than 80 characters!")
//    private String city;
//
//    @NotNull(message = "Province is required!")
//    @NotBlank(message = "Province cannot be empty!")
//    @Size(max = 80, message = "Province cannot be longer than 80 characters!")
//    private String province;
//
//    @NotNull(message = "Country is required!")
//    @NotBlank(message = "Country cannot be empty!")
//    @Size(max = 56, message = "Country cannot be longer than 56 characters!")
//    private String country;
//
//    @NotNull(message = "Email is required!")
//    @NotBlank(message = "Email cannot be empty!")
//    @Size(max = 255, message = "Email cannot be longer than 255 characters!")
//    private String email;
//
//    @NotNull(message = "Phone number is required!")
//    @NotBlank(message = "Phone number cannot be empty!")
//    @Size(max = 20, message = "Phone number cannot be longer than 20 characters!")
//    private String phoneNumber;
//
//    private Boolean isArchived;
//
//    private Long openingHoursId;
//}

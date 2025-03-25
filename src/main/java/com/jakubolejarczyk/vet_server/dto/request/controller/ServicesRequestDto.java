package com.jakubolejarczyk.vet_server.dto.request.controller;

import com.jakubolejarczyk.vet_server.domain.dependent.ServicesDomain;
import com.jakubolejarczyk.vet_server.dto.base.TokenRequestDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicesRequestDto extends TokenRequestDto implements ServicesDomain {
    private Long id;

    @NotNull(message = "Name is required!")
    @NotBlank(message = "Name cannot be empty!")
    @Size(max = 255, message = "Name cannot be longer than 255 characters!")
    private String name;

    @NotNull(message = "Description is required!")
    @NotBlank(message = "Description cannot be empty!")
    @Size(max = 255, message = "Description cannot be longer than 255 characters!")
    private String description;

    @NotNull(message = "Category is required!")
    @NotBlank(message = "Category cannot be empty!")
    @Size(max = 255, message = "Category cannot be longer than 255 characters!")
    private String category;

    @NotNull(message = "Duration minutes is required!")
    @NotBlank(message = "Duration minutes cannot be empty!")
    @Size(max = 255, message = "Duration minutes cannot be longer than 255 characters!")
    private String durationMinutes;

    @NotNull(message = "Price is required!")
    @NotBlank(message = "Price cannot be empty!")
    @Size(max = 255, message = "Price cannot be longer than 255 characters!")
    private String price;

    @NotNull(message = "Is available is required!")
    @NotBlank(message = "Is available cannot be empty!")
    @Size(max = 255, message = "Is available cannot be longer than 255 characters!")
    private String isAvailable;

    private Long clinicId;
}

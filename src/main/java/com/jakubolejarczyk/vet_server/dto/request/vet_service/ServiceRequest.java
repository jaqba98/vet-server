package com.jakubolejarczyk.vet_server.dto.request.vet_service;

import com.jakubolejarczyk.vet_server.domain.dependent.ServiceDomain;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ServiceRequest extends TokenRequest implements ServiceDomain {
    private Long id;

    @NotNull(message = "Name is required!")
    @NotBlank(message = "Name cannot be empty!")
    @Size(max = 255, message = "Name cannot be longer than 255 characters!")
    private String entityName;

    @NotNull(message = "Description is required!")
    @NotBlank(message = "Description cannot be empty!")
    @Size(max = 255, message = "Description cannot be longer than 255 characters!")
    private String description;

    @NotNull(message = "Category is required!")
    @NotBlank(message = "Category cannot be empty!")
    @Size(max = 255, message = "Category cannot be longer than 255 characters!")
    private String category;

    @NotNull(message = "Duration minutes is required!")
    private Long durationMinutes;

    @NotNull(message = "Price is required!")
    private BigDecimal price;

    @NotNull(message = "Is available is required!")
    private Boolean isAvailable;

    @NotNull(message = "Is archived is required!")
    private Boolean isArchived;

    @NotNull(message = "Clinic id is required!")
    private Long clinicId;
}

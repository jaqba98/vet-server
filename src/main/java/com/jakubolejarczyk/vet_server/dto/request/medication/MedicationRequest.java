package com.jakubolejarczyk.vet_server.dto.request.medication;

import com.jakubolejarczyk.vet_server.domain.dependent.MedicationDomain;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
public class MedicationRequest extends TokenRequest implements MedicationDomain {
    private Long id;

    @NotNull(message = "Is archived is required!")
    private Boolean isArchived;

    @NotNull(message = "Name is required!")
    @NotBlank(message = "Name cannot be empty!")
    @Size(max = 255, message = "Name cannot be longer than 255 characters!")
    private String entityName;

    @NotNull(message = "Description is required!")
    @NotBlank(message = "Description cannot be empty!")
    @Size(max = 255, message = "Description cannot be longer than 255 characters!")
    private String description;

    @NotNull(message = "Manufacturer is required!")
    @NotBlank(message = "Manufacturer cannot be empty!")
    @Size(max = 255, message = "Manufacturer cannot be longer than 255 characters!")
    private String manufacturer;

    @NotNull(message = "Dose is required!")
    @NotBlank(message = "Dose cannot be empty!")
    @Size(max = 255, message = "Dose cannot be longer than 255 characters!")
    private String dose;

    @NotNull(message = "Quantity in stock is required!")
    private Long quantityInStock;

    @NotNull(message = "Expiration date is required!")
    private LocalDate expirationDate;

    @NotNull(message = "Price is required!")
    private BigDecimal price;

    @NotNull(message = "Is available is required!")
    private Boolean isAvailable;

    private Long clinicId;
}

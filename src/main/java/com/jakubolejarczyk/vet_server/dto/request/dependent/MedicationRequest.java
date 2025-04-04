package com.jakubolejarczyk.vet_server.dto.request.dependent;

import com.jakubolejarczyk.vet_server.domain.dependent.MedicationDomain;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class MedicationRequest extends TokenRequest implements MedicationDomain {
    @NotNull(message = "Id is required!")
    private Long id;

    @NotNull(message = "Full name is required!")
    @NotBlank(message = "Full name cannot be empty!")
    @Size(max = 255, message = "Full name cannot be longer than 255 characters!")
    private String fullName;

    @NotNull(message = "Description is required!")
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

    @NotNull(message = "Clinic id is required!")
    private Long clinicId;
}

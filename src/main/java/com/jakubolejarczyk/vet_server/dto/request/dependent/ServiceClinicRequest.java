package com.jakubolejarczyk.vet_server.dto.request.dependent;

import com.jakubolejarczyk.vet_server.domain.dependent.ServiceClinicDomain;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ServiceClinicRequest extends TokenRequest implements ServiceClinicDomain {
    @NotNull(message = "Id is required!")
    private Long id;

    @NotNull(message = "Full name is required!")
    @NotBlank(message = "Full name cannot be empty!")
    @Size(max = 255, message = "Full name cannot be longer than 255 characters!")
    private String fullName;

    @NotNull(message = "Description is required!")
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

    @NotNull(message = "Clinic id is required!")
    private Long clinicId;
}

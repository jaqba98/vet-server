package com.jakubolejarczyk.vet_server.dto.request.dependent;

import com.jakubolejarczyk.vet_server.domain.dependent.VetDomain;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class VetRequest extends TokenRequest implements VetDomain {
    @NotNull(message = "Id is required!")
    private Long id;

    @Size(max = 255, message = "License Number cannot be longer than 255 characters!")
    private String licenseNumber;

    private LocalDate licenseIssueDate;

    private LocalDate licenseExpiryDate;

    @Size(max = 255, message = "Specialization cannot be longer than 255 characters!")
    private String specialization;

    private Long yearsOfExperience;

    @NotNull(message = "Account id is required!")
    private Long accountId;
}

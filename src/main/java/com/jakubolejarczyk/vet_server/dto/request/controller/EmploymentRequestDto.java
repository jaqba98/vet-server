package com.jakubolejarczyk.vet_server.dto.request.controller;

import com.jakubolejarczyk.vet_server.domain.dependent.EmploymentDomain;
import com.jakubolejarczyk.vet_server.dto.base.TokenRequestDto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmploymentRequestDto extends TokenRequestDto implements EmploymentDomain {
    private Long id;

    @NotNull(message = "Is owner is required!")
    private Boolean isOwner;

    @NotNull(message = "Is archived is required!")
    private Boolean isArchived;

    private Long accountId;

    private Long clinicId;
}

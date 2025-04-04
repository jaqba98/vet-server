package com.jakubolejarczyk.vet_server.dto.request.dependent;

import com.jakubolejarczyk.vet_server.domain.dependent.EmploymentDomain;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmploymentRequest extends TokenRequest implements EmploymentDomain {
    @NotNull(message = "Id is required!")
    private Long id;

    @NotNull(message = "Is Owner is required!")
    private Boolean isOwner;

    @NotNull(message = "Account id is required!")
    private Long accountId;

    @NotNull(message = "Clinic id is required!")
    private Long clinicId;
}

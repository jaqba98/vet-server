package com.jakubolejarczyk.vet_server.model;

import com.jakubolejarczyk.vet_server.domain.ClinicAccountDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClinicAccount extends ClinicAccountDomain {
    private Long id;

    private Long accountId;

    private Long clinicId;
}

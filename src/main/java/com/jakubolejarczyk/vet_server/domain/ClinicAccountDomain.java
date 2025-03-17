package com.jakubolejarczyk.vet_server.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicAccountDomain {
    private Long id;
    private Long accountId;
    private Long clinicId;
}

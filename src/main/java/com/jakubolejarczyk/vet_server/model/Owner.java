package com.jakubolejarczyk.vet_server.model;

import com.jakubolejarczyk.vet_server.domain.OwnerDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Owner extends OwnerDomain {
    private Long id;

    private Long accountId;

    private Long clinicId;
}

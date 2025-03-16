package com.jakubolejarczyk.vet_server.model;

import com.jakubolejarczyk.vet_server.domain.ClinicAccountDomain;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClinicAccount extends ClinicAccountDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "clinic_id", nullable = false)
    private Long clinicId;
}

package com.jakubolejarczyk.vet_server.model;

import com.jakubolejarczyk.vet_server.domain.OwnerDomain;
import jakarta.persistence.*;

@Entity
public class Owner extends OwnerDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "clinic_id", nullable = false)
    private Long clinicId;
}

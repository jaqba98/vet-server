package com.jakubolejarczyk.vet_server.model;

import com.jakubolejarczyk.vet_server.domain.ClientDomain;
import jakarta.persistence.*;

@Entity
public class Client extends ClientDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id", nullable = false)
    private Long accountId;
}

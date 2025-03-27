package com.jakubolejarczyk.vet_server.model.dependent;

import com.jakubolejarczyk.vet_server.domain.dependent.EmploymentDomain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Employment implements EmploymentDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_owner", nullable = false)
    private Boolean isOwner;

    @Column(name = "is_archived", nullable = false)
    private Boolean isArchived;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "clinic_id", nullable = false)
    private Long clinicId;
}

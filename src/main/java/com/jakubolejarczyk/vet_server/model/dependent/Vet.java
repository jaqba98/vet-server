package com.jakubolejarczyk.vet_server.model.dependent;

import com.jakubolejarczyk.vet_server.domain.dependent.VetDomain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Vet implements VetDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_archived", nullable = false)
    private Boolean isArchived;

    @Column(name = "license_number")
    private String licenseNumber;

    @Column(name = "license_issue_date")
    private Date licenseIssueDate;

    @Column(name = "license_expiry_date")
    private Date licenseExpiryDate;

    @Column
    private String specialization;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "opening_hours_id", nullable = false)
    private Long openingHoursId;
}

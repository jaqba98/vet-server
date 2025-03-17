package com.jakubolejarczyk.vet_server.model;

import com.jakubolejarczyk.vet_server.domain.VetDomain;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Vet extends VetDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_number")
    private String licenseNumber;

    @Column(name = "license_issue_date")
    private LocalDate licenseIssueDate;

    @Column(name = "license_expiry_date")
    private LocalDate licenseExpiryDate;

    @Column(name = "specializations")
    private String specializations;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "opening_hours_id", nullable = false)
    private Long openingHoursId;
}

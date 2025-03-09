package com.jakubolejarczyk.vet_server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
public class Vet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_number")
    private String licenseNumber;

    @Column(name = "license_issue_date")
    private LocalDate licenseIssueDate;

    @Column(name = "license_expiry_date")
    private LocalDate licenseExpiryDate;

    @Column()
    private String specializations;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account accountId;

    @OneToOne
    @JoinColumn(name = "opening_hours_id", nullable = false)
    private OpeningHours openingHoursId;
}

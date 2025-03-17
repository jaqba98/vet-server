package com.jakubolejarczyk.vet_server.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class VetDomain {
    private Long id;
    private String licenseNumber;
    private LocalDate licenseIssueDate;
    private LocalDate licenseExpiryDate;
    private String specializations;
    private Integer yearsOfExperience;
    private Long accountId;
    private Long openingHoursId;
}

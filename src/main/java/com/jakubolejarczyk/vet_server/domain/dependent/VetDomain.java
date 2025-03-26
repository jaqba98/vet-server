package com.jakubolejarczyk.vet_server.domain.dependent;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

import java.time.LocalDate;

public class VetDomain extends BaseDomain {
    protected String licenseNumber;
    protected LocalDate licenseIssueDate;
    protected LocalDate licenseExpiryDate;
    protected String specialization;
    protected Integer yearsOfExperience;
    protected Long accountId;
    protected Long openingHoursId;
}

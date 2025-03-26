package com.jakubolejarczyk.vet_server.domain.dependent;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

import java.time.LocalDate;

public interface VetDomain extends BaseDomain {
    String getLicenseNumber();
    LocalDate getLicenseIssueDate();
    LocalDate getLicenseExpiryDate();
    String getSpecialization();
    Integer getYearsOfExperience();
    Long getAccountId();
    Long getOpeningHoursId();
}

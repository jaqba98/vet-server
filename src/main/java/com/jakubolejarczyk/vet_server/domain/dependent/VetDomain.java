package com.jakubolejarczyk.vet_server.domain.dependent;

import java.time.LocalDate;

public interface VetDomain {
    Long getId();
    String getLicenseNumber();
    LocalDate getLicenseIssueDate();
    LocalDate getLicenseExpiryDate();
    String getSpecializations();
    Integer getYearsOfExperience();
    Long getAccountId();
    Long getOpeningHoursId();
}

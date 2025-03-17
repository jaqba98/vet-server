package com.jakubolejarczyk.vet_server.domain;

import java.time.LocalDate;

public interface VetDomain {
    Long id();

    String getLicenseNumber();

    LocalDate getLicenseIssueDate();

    LocalDate getLicenseExpiryDate();

    String getSpecializations();

    Integer getYearsOfExperience();

    Long getAccountId();

    Long getOpeningHoursId();
}

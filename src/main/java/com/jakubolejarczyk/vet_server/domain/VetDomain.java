package com.jakubolejarczyk.vet_server.domain;

import java.time.LocalDate;

public interface VetDomain {
    Long getId();
    void setId(Long id);

    String getLicenseNumber();
    void setLicenseNumber(String licenseNumber);

    LocalDate getLicenseIssueDate();
    void setLicenseIssueDate(LocalDate licenseIssueDate);

    LocalDate getLicenseExpiryDate();
    void setLicenseExpiryDate(LocalDate licenseExpiryDate);

    String getSpecializations();
    void setSpecializations(String specializations);

    Integer getYearsOfExperience();
    void setYearsOfExperience(Integer yearsOfExperience);

    Long getAccountId();
    void setAccountId(Long accountId);

    Long getOpeningHoursId();
    void setOpeningHoursId(Long openingHoursId);
}

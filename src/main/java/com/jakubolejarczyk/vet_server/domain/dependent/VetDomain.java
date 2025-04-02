package com.jakubolejarczyk.vet_server.domain.dependent;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

import java.sql.Date;

public interface VetDomain extends BaseDomain {
    String getLicenseNumber();
    Date getLicenseIssueDate();
    Date getLicenseExpiryDate();
    String getSpecialization();
    Integer getYearsOfExperience();
    Long getAccountId();
    Long getOpeningHoursId();
}

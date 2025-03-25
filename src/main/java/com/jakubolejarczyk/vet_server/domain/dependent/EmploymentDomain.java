package com.jakubolejarczyk.vet_server.domain.dependent;

public interface EmploymentDomain {
    Long getId();
    Boolean getIsOwner();
    Boolean getIsArchived();
    Long getAccountId();
    Long getClinicId();
}

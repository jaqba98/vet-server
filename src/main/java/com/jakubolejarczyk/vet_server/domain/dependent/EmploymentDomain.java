package com.jakubolejarczyk.vet_server.domain.dependent;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

public interface EmploymentDomain extends BaseDomain {
    Boolean getIsOwner();
    Long getAccountId();
    Long getClinicId();
}

package com.jakubolejarczyk.vet_server.domain.dependent;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

public class EmploymentDomain extends BaseDomain {
    protected Boolean isOwner;
    protected Long accountId;
    protected Long clinicId;
}

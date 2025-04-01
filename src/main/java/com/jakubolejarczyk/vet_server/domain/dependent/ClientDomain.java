package com.jakubolejarczyk.vet_server.domain.dependent;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

public interface ClientDomain extends BaseDomain {
    String getEmail();
    String getPhoneNumber();
    String getFirstName();
    String getLastName();
    Long getClinicId();
}

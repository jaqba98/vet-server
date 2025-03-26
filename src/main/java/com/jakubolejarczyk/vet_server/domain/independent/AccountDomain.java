package com.jakubolejarczyk.vet_server.domain.independent;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

public interface AccountDomain extends BaseDomain {
    String getEmail();
    String getPassword();
    String getFirstName();
    String getLastName();
    String getRole();
    String getPictureUrl();
}

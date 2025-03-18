package com.jakubolejarczyk.vet_server.domain;

public interface ClinicAccountDomain {
    Long getId();
    void setId(Long id);

    Long getAccountId();
    void setAccountId(Long accountId);

    Long getClinicId();
    void setClinicId(Long clinicId);
}

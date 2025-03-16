package com.jakubolejarczyk.vet_server.domain;

public abstract class ClinicAccountDomain {
    protected abstract Long getId();

    protected abstract Long getAccountId();

    protected abstract Long getClinicId();
}

package com.jakubolejarczyk.vet_server.domain;

public abstract class OwnerDomain {
    abstract Long getId();

    abstract String getAccountId();

    abstract String getClinicId();
}

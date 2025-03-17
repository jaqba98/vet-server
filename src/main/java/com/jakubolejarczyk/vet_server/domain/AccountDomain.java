package com.jakubolejarczyk.vet_server.domain;

public interface AccountDomain {
    Long getId();

    String getEmail();

    String getPassword();

    String getFirstName();

    String getLastName();

    String getRole();

    String getPictureUrl();

    Boolean getIsVerified();
}

package com.jakubolejarczyk.vet_server.domain.independent;

public interface AccountDomain {
    Long getId();
    String getEmail();
    String getPassword();
    String getFirstName();
    String getLastName();
    String getRole();
    String getPictureUrl();
}

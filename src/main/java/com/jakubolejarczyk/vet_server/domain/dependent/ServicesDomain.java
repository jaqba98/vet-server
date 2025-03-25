package com.jakubolejarczyk.vet_server.domain.dependent;

public interface ServicesDomain {
    Long getId();
    String getDescription();
    String getCategory();
    String getDurationMinutes();
    String getPrice();
    String getIsAvailable();
    Long getClinicId();
}

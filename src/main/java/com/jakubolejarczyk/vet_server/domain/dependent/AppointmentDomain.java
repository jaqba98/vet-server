package com.jakubolejarczyk.vet_server.domain.dependent;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

import java.sql.Timestamp;

public interface AppointmentDomain extends BaseDomain {
    String getFullName();
    Timestamp getDateAndHour();
    String getType();
    String getStatus();
    String getReason();
    String getNotes();
    Long getClinicId();
    Long getVetId();
    Long getPetId();
}

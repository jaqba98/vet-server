package com.jakubolejarczyk.vet_server.domain.independent;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

import java.time.LocalDate;

public interface MedicalRecordDomain extends BaseDomain {
    String getDiagnosis();
    String getTreatment();
    String getProcedures();
    LocalDate getNextAppointment();
    String getStatus();
    String getNotes();
}

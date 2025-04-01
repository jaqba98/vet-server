package com.jakubolejarczyk.vet_server.domain.independent;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

import java.sql.Date;

public interface MedicalRecordDomain extends BaseDomain {
    String getDiagnosis();
    String getTreatment();
    String getProcedures();
    Date getNextAppointment();
    String getStatus();
    String getNotes();
}

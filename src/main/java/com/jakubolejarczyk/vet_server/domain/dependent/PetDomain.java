package com.jakubolejarczyk.vet_server.domain.dependent;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

import java.math.BigDecimal;
import java.sql.Date;

public interface PetDomain extends BaseDomain {
    String getName();
    String getSpecies();
    String getBreed();
    Date getDateOfBirth();
    BigDecimal getWeightKg();
    String getColor();
    Boolean getSterilized();
    String getPictureUrl();
    String getMicrochipNumber();
    String getMedicalNotes();
    Long getClientId();
}

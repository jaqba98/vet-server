package com.jakubolejarczyk.vet_server.domain.dependent;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface PetDomain extends BaseDomain {
    String getFullName();
    String getSpecies();
    String getBreed();
    LocalDate getDateOfBirth();
    BigDecimal getWeightKg();
    String getColor();
    Boolean getSterilized();
    String getPictureUrl();
    String getMicrochipNumber();
    String getMedicalNotes();
    Long getClientId();
}

package com.jakubolejarczyk.vet_server.domain.dependent;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface MedicationDomain extends BaseDomain {
    String getEntityName();
    String getDescription();
    String getManufacturer();
    String getDose();
    Integer getQuantityInStock();
    LocalDate getExpirationDate();
    BigDecimal getPrice();
    Boolean getIsAvailable();
    Long getClinicId();
}

package com.jakubolejarczyk.vet_server.domain.dependent;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

import java.math.BigDecimal;
import java.sql.Date;

public interface MedicationDomain extends BaseDomain {
    String getName();
    String getDescription();
    String getManufacturer();
    String getDose();
    Integer getQuantityInStock();
    Date getExpirationDate();
    BigDecimal getPrice();
    Boolean getIsAvailable();
    Long getClinicId();
}

package com.jakubolejarczyk.vet_server.domain.dependent;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

import java.math.BigDecimal;

public interface ServiceDomain extends BaseDomain {
    String getEntityName();
    String getDescription();
    String getCategory();
    Integer getDurationMinutes();
    BigDecimal getPrice();
    Boolean getIsAvailable();
    Long getClinicId();
}

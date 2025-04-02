package com.jakubolejarczyk.vet_server.domain.dependent;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

import java.math.BigDecimal;

public interface ServiceClinicDomain extends BaseDomain {
    String getEntityName();
    String getDescription();
    String getCategory();
    Long getDurationMinutes();
    BigDecimal getPrice();
    Boolean getIsAvailable();
    Long getClinicId();
}

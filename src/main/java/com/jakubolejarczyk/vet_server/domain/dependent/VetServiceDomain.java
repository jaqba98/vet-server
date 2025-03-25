package com.jakubolejarczyk.vet_server.domain.dependent;

import java.math.BigDecimal;

public interface VetServiceDomain {
    Long getId();
    String getName();
    String getDescription();
    String getCategory();
    Integer getDurationMinutes();
    BigDecimal getPrice();
    Boolean getIsAvailable();
    Boolean getIsArchived();
    Long getClinicId();
}

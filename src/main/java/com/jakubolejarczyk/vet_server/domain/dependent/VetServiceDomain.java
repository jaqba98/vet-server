package com.jakubolejarczyk.vet_server.domain.dependent;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

import java.math.BigDecimal;

public class VetServiceDomain extends BaseDomain {
    protected String name;
    protected String description;
    protected String category;
    protected Integer durationMinutes;
    protected BigDecimal price;
    protected Boolean isAvailable;
    protected Long clinicId;
}

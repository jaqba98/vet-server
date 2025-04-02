package com.jakubolejarczyk.vet_server.domain.relation;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

public interface AppointmentServicesDomain extends BaseDomain {
    Long getAppointmentId();
    Long getServiceId();
}

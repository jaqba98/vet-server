package com.jakubolejarczyk.vet_server.dto.request.relation;

import com.jakubolejarczyk.vet_server.domain.relation.AppointmentServiceClinicDomain;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentServiceClinicRequest extends TokenRequest implements AppointmentServiceClinicDomain {
    @NotNull(message = "Id is required!")
    private Long id;

    @NotNull(message = "Appointment id is required!")
    private Long appointmentId;

    @NotNull(message = "Service clinic id is required!")
    private Long serviceClinicId;
}

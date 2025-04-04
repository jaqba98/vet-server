package com.jakubolejarczyk.vet_server.dto.request.relation;

import com.jakubolejarczyk.vet_server.domain.relation.AppointmentMedicationDomain;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentMedicationRequest extends TokenRequest implements AppointmentMedicationDomain {
    @NotNull(message = "Id is required!")
    private Long id;

    @NotNull(message = "Appointment id is required!")
    private Long appointmentId;

    @NotNull(message = "Medication id is required!")
    private Long medicationId;
}

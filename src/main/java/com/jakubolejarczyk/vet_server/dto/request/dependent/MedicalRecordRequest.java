package com.jakubolejarczyk.vet_server.dto.request.dependent;

import com.jakubolejarczyk.vet_server.domain.dependent.MedicalRecordDomain;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MedicalRecordRequest extends TokenRequest implements MedicalRecordDomain {
    @NotNull(message = "Id is required!")
    private Long id;

    private String diagnosis;

    private String treatment;

    private String procedures;

    private LocalDate nextAppointment;

    @Size(max = 255, message = "Status cannot be longer than 255 characters!")
    private String status;

    private String notes;

    @NotNull(message = "Appointment id is required!")
    private Long appointmentId;
}

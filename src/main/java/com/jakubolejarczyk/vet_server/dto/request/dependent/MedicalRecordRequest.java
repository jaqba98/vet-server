package com.jakubolejarczyk.vet_server.dto.request.dependent;

import com.jakubolejarczyk.vet_server.domain.dependent.MedicalRecordDomain;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import jakarta.validation.constraints.NotBlank;
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

    private Boolean isArchived;

    @NotNull(message = "Diagnosis is required!")
    private String diagnosis;

    @NotNull(message = "Treatment is required!")
    private String treatment;

    @NotNull(message = "Procedures is required!")
    private String procedures;

    @NotNull(message = "Next Appointment is required!")
    private LocalDate nextAppointment;

    @NotNull(message = "Status is required!")
    @NotBlank(message = "Status cannot be empty!")
    @Size(max = 255, message = "Status cannot be longer than 255 characters!")
    private String status;

    @NotNull(message = "Notes is required!")
    private String notes;

    @NotNull(message = "Appointment id is required!")
    private Long appointmentId;
}

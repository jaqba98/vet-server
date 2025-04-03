package com.jakubolejarczyk.vet_server.dto.request.medical_record;

import com.jakubolejarczyk.vet_server.domain.independent.MedicalRecordDomain;
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
    private Long id;

    @NotNull(message = "Is archived is required!")
    private Boolean isArchived;

    @NotNull(message = "Diagnosis is required!")
    @NotBlank(message = "Diagnosis cannot be empty!")
    @Size(max = 255, message = "Diagnosis cannot be longer than 255 characters!")
    private String diagnosis;

    @NotNull(message = "Treatment is required!")
    @NotBlank(message = "Treatment cannot be empty!")
    @Size(max = 255, message = "Treatment cannot be longer than 255 characters!")
    private String treatment;

    @NotNull(message = "Procedures is required!")
    @NotBlank(message = "Procedures cannot be empty!")
    @Size(max = 255, message = "Procedures cannot be longer than 255 characters!")
    private String procedures;

    @NotNull(message = "Next Appointment is required!")
    private LocalDate nextAppointment;

    @NotNull(message = "Status is required!")
    @NotBlank(message = "Status cannot be empty!")
    @Size(max = 255, message = "Status cannot be longer than 255 characters!")
    private String status;

    @NotNull(message = "Notes is required!")
    @NotBlank(message = "Notes cannot be empty!")
    @Size(max = 255, message = "Notes cannot be longer than 255 characters!")
    private String notes;
}

package com.jakubolejarczyk.vet_server.dto.request.appointment;

import com.jakubolejarczyk.vet_server.domain.dependent.AppointmentDomain;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class AppointmentRequest extends TokenRequest implements AppointmentDomain {
    private Long id;

    @NotNull(message = "Is archived is required!")
    private Boolean isArchived;

    @NotNull(message = "Date And Hour are required!")
    private Timestamp dateAndHour;

    @NotNull(message = "Type is required!")
    @NotBlank(message = "Type cannot be empty!")
    @Size(max = 255, message = "Type cannot be longer than 255 characters!")
    private String type;

    @NotNull(message = "Status is required!")
    @NotBlank(message = "Status cannot be empty!")
    @Size(max = 255, message = "Status cannot be longer than 255 characters!")
    private String status;

    @NotNull(message = "Reason is required!")
    @NotBlank(message = "Reason cannot be empty!")
    @Size(max = 255, message = "Reason cannot be longer than 255 characters!")
    private String reason;

    @NotNull(message = "Notes is required!")
    @NotBlank(message = "Notes cannot be empty!")
    @Size(max = 255, message = "Notes cannot be longer than 255 characters!")
    private String notes;

    @NotNull(message = "Clinic ID is required!")
    private Long clinicId;

    @NotNull(message = "Vet ID is required!")
    private Long vetId;

    @NotNull(message = "Pet ID is required!")
    private Long petId;

    @NotNull(message = "Invoice ID is required!")
    private Long invoiceId;

    @NotNull(message = "Medical Record ID is required!")
    private Long medicalRecordId;
}

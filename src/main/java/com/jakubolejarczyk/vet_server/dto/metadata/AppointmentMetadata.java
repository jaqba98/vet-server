package com.jakubolejarczyk.vet_server.dto.metadata;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class AppointmentMetadata {
    private Map<Long, String> clinicId;

    private Map<Long, String> vetId;

    private Map<Long, String> petId;

    private Map<Long, String> invoiceId;

    private Map<Long, String> medicalRecordId;
}

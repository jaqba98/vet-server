package com.jakubolejarczyk.vet_server.dto.data.relation;

import com.jakubolejarczyk.vet_server.model.relation.AppointmentMedication;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AppointmentMedicationData {
    private List<AppointmentMedication> appointmentMedications;
}

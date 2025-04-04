package com.jakubolejarczyk.vet_server.dto.data.relation;

import com.jakubolejarczyk.vet_server.model.relation.AppointmentServiceClinic;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AppointmentServiceClinicData {
    private List<AppointmentServiceClinic> appointmentServiceClinics;
}

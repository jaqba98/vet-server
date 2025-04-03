package com.jakubolejarczyk.vet_server.dto.data;

import com.jakubolejarczyk.vet_server.model.dependent.Appointment;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AppointmentData {
    List<Appointment> appointments;
}

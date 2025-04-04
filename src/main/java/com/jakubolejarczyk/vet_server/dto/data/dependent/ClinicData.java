package com.jakubolejarczyk.vet_server.dto.data.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ClinicData {
    private List<Clinic> clinics;
}

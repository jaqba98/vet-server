package com.jakubolejarczyk.vet_server.dto.data.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.ServiceClinic;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ServiceClinicData {
    private List<ServiceClinic> serviceClinics;
}

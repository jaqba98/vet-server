package com.jakubolejarczyk.vet_server.service.relation;

import com.jakubolejarczyk.vet_server.model.relation.AppointmentServiceClinic;
import com.jakubolejarczyk.vet_server.repository.relation.AppointmentServiceClinicRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceClinicService extends BaseService<AppointmentServiceClinic> {
    public AppointmentServiceClinicService(AppointmentServiceClinicRepository repository) {
        super(repository);
    }
}

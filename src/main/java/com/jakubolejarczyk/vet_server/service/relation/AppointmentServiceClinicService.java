package com.jakubolejarczyk.vet_server.service.relation;

import com.jakubolejarczyk.vet_server.model.relation.AppointmentServiceClinic;
import com.jakubolejarczyk.vet_server.repository.relation.AppointmentServiceClinicRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceClinicService extends BaseService<AppointmentServiceClinic, AppointmentServiceClinicRepository> {
    public AppointmentServiceClinicService(@Qualifier("appointmentServiceClinicRepository") AppointmentServiceClinicRepository repository) {
        super(repository);
    }
}

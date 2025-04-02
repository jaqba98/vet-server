package com.jakubolejarczyk.vet_server.service.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Appointment;
import com.jakubolejarczyk.vet_server.repository.dependent.AppointmentRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService extends BaseService<Appointment> {
    public AppointmentService(AppointmentRepository repository) {
        super(repository);
    }
}

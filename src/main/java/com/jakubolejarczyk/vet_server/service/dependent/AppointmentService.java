package com.jakubolejarczyk.vet_server.service.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Appointment;
import com.jakubolejarczyk.vet_server.repository.dependent.AppointmentRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService extends BaseService<Appointment, AppointmentRepository> {
    public AppointmentService(@Qualifier("appointmentRepository") AppointmentRepository repository) {
        super(repository);
    }

    public List<Appointment> findAllByClinicIdIn(List<Long> clinicId) {
        return repository.findAllByClinicIdIn(clinicId);
    }
}

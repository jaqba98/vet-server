package com.jakubolejarczyk.vet_server.service.relation;

import com.jakubolejarczyk.vet_server.model.relation.AppointmentMedication;
import com.jakubolejarczyk.vet_server.repository.relation.AppointmentMedicationRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class AppointmentMedicationService extends BaseService<AppointmentMedication> {
    public AppointmentMedicationService(AppointmentMedicationRepository repository) {
        super(repository);
    }
}

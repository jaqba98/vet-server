package com.jakubolejarczyk.vet_server.service.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Medication;
import com.jakubolejarczyk.vet_server.repository.dependent.MedicationRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class MedicationService extends BaseService<Medication> {
    public MedicationService(MedicationRepository repository) {
        super(repository);
    }
}

package com.jakubolejarczyk.vet_server.service.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Medication;
import com.jakubolejarczyk.vet_server.repository.dependent.MedicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MedicationService {
    private final MedicationRepository repository;

    public Medication create(Medication medication) {
        return repository.save(medication);
    }

    public List<Medication> findAllByClinicIds(List<Long> clinicIds) {
        return repository.findAllByClinicIds(clinicIds);
    }
}

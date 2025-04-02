package com.jakubolejarczyk.vet_server.service.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Service;
import com.jakubolejarczyk.vet_server.repository.dependent.VetServiceRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class VetServiceService {
    private final VetServiceRepository repository;

    public Service create(Service vetService) {
        return repository.save(vetService);
    }

    public Optional<Service> findById(Long id) {
        return repository.findById(id);
    }

    public List<Service> findAllByClinicIds(List<Long> clinicIds) {
        return repository.findAllByClinicIds(clinicIds);
    }

    public void updateIsArchived(List<Long> ids, Boolean isArchived) {
        repository.updateIsArchived(ids, isArchived);
    }
}

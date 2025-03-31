package com.jakubolejarczyk.vet_server.service.crud.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.repository.dependent.ClinicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClinicService {
    private final ClinicRepository repository;

    public Clinic create(Clinic clinic) {
        return repository.save(clinic);
    }

    public Optional<Clinic> findById(Long id) {
        return repository.findById(id);
    }

    public List<Clinic> findAllById(List<Long> ids) {
        return repository.findAllById(ids);
    }

    public Optional<Clinic> findByName(String name) {
        return repository.findByName(name);
    }

    public void updateIsArchived(List<Long> ids, Boolean isArchived) {
        repository.updateIsArchived(ids, isArchived);
    }
}

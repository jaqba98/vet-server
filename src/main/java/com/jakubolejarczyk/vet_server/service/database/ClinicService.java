package com.jakubolejarczyk.vet_server.service.database;

import com.jakubolejarczyk.vet_server.model.Clinic;
import com.jakubolejarczyk.vet_server.repository.ClinicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClinicService {
    private final ClinicRepository repository;

    public void create(Clinic clinic) {
        repository.save(clinic);
    }

    public List<Clinic> read() {
        return repository.findAll();
    }

    public void update(Clinic clinic) {
        repository.save(clinic);
    }

    public void delete(Clinic clinic) {
        Long id = clinic.getId();
        repository.deleteById(id);
    }

    public void deleteByIds(List<Long> ids) {
        repository.deleteAllByIdInBatch(ids);
    }

    public Boolean uniqueName(String name) {
        return repository.findByName(name).isEmpty();
    }

    public Optional<Clinic> findById(Long id) {
        return repository.findById(id);
    }
}

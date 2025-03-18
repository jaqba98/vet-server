package com.jakubolejarczyk.vet_server.service.database;

import com.jakubolejarczyk.vet_server.model.Clinic;
import com.jakubolejarczyk.vet_server.repository.ClinicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClinicService {
    private final ClinicRepository repository;

    public Clinic create(Clinic clinic) {
        repository.save(clinic);
        return clinic;
    }

    public ArrayList<Clinic> read() {
        return new ArrayList<>(repository.findAll());
    }

    public void update(Clinic clinic) {
        repository.save(clinic);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void deleteByIds(ArrayList<Long> ids) {
        repository.deleteAllByIdInBatch(ids);
    }

    public Boolean uniqueName(String name) {
        return repository.findByName(name).isEmpty();
    }

    public Optional<Clinic> findById(Long id) {
        return repository.findById(id);
    }

    public List<Clinic> findAllById(List<Long> ids) {
        return repository.findAllById(ids);
    }
}

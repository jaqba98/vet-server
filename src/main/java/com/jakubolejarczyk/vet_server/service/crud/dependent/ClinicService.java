package com.jakubolejarczyk.vet_server.service.crud.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.model.independent.OpeningHours;
import com.jakubolejarczyk.vet_server.repository.dependent.ClinicRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

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

    public List<Clinic> read() {
        return repository.findAll();
    }

    public Clinic update(Clinic clinic) throws Exception {
        val clinicById = repository.findById(clinic.getId());
        if (clinicById.isEmpty()) {
            throw new Exception("The update method cannot update a record that does not exist!");
        }
        repository.save(clinic);
        return clinic;
    }

    public Clinic delete(Clinic clinic) {
        repository.deleteById(clinic.getId());
        return clinic;
    }

    public void deleteAllByIdInBatch(List<Long> ids) {
        repository.deleteAllByIdInBatch(ids);
    }

    public Optional<Clinic> findByName(String name) {
        return repository.findByName(name);
    }

    public Optional<Clinic> findById(Long id) {
        return repository.findById(id);
    }

    public List<Clinic> findAllById(List<Long> ids) {
        return repository.findAllById(ids);
    }
}

package com.jakubolejarczyk.vet_server.service.crud.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
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
        return repository.save(clinic);
    }

    public List<Clinic> read() {
        return repository.findAll();
    }

    public void update(Clinic clinic) throws Exception {
        val clinicId = clinic.getId();
        val clinicById = repository.findById(clinicId);
        if (clinicById.isEmpty()) {
            throw new Exception("The update method cannot be performed! The clinic does not exist!");
        }
        repository.save(clinic);
    }

    public void delete(Long clinicId) {
        repository.deleteById(clinicId);
    }

    public Optional<Clinic> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Clinic> findAllByIds(List<Long> ids) {
        return repository.findAllByIds(ids);
    }

    public void updateIsArchived(List<Long> ids, Boolean isArchived) {
        repository.updateIsArchived(ids, isArchived);
    }
}

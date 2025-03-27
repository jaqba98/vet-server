package com.jakubolejarczyk.vet_server.service.crud.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.repository.dependent.ClinicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}

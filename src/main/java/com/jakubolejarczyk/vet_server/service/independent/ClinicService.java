package com.jakubolejarczyk.vet_server.service.independent;

import com.jakubolejarczyk.vet_server.model.independent.Clinic;
import com.jakubolejarczyk.vet_server.repository.independent.ClinicRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClinicService extends BaseService<Clinic, ClinicRepository> {
    public ClinicService(@Qualifier("clinicRepository") ClinicRepository repository) {
        super(repository);
    }

    public Optional<Clinic> findByFullName(String fullName) {
        return repository.findByFullName(fullName);
    }

    public Optional<Clinic> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}

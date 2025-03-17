package com.jakubolejarczyk.vet_server.service.database;

import com.jakubolejarczyk.vet_server.model.Owner;
import com.jakubolejarczyk.vet_server.repository.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OwnerService {
    private final OwnerRepository repository;

    public void create(Long accountId, Long clinicId) {
        Owner owner = new Owner();
        owner.setAccountId(accountId);
        owner.setClinicId(clinicId);
        repository.save(owner);
    }

    public List<Owner> read() {
        return repository.findAll();
    }

    public void update(Owner owner) {
        repository.save(owner);
    }

    public void delete(Owner owner) {
        Long id = owner.getId();
        repository.deleteById(id);
    }
}

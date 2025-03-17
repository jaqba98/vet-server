package com.jakubolejarczyk.vet_server.service.database;

import com.jakubolejarczyk.vet_server.model.Owner;
import com.jakubolejarczyk.vet_server.repository.OwnerRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OwnerService {
    private final OwnerRepository repository;

    public Owner create(Long accountId, Long clinicId) {
        val owner = new Owner();
        owner.setAccountId(accountId);
        owner.setClinicId(clinicId);
        repository.save(owner);
        return owner;
    }

    public List<Owner> read() {
        return repository.findAll();
    }

    public void update(Owner owner) {
        repository.save(owner);
    }

    public void delete(Owner owner) {
        val id = owner.getId();
        repository.deleteById(id);
    }
}

package com.jakubolejarczyk.vet_server.service.crud.relation;

import com.jakubolejarczyk.vet_server.model.relation.ClinicAccount;
import com.jakubolejarczyk.vet_server.model.relation.Owner;
import com.jakubolejarczyk.vet_server.repository.relation.OwnerRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OwnerService {
    private final OwnerRepository repository;

    public Owner create(Owner owner) {
        repository.save(owner);
        return owner;
    }

    public List<Owner> read() {
        return repository.findAll();
    }

    public Owner update(Owner owner) throws Exception {
        val ownerById = repository.findById(owner.getId());
        if (ownerById.isEmpty()) {
            throw new Exception("The update method cannot update a record that does not exist!");
        }
        repository.save(owner);
        return owner;
    }

    public Owner delete(Owner owner) {
        repository.deleteById(owner.getId());
        return owner;
    }

    public void deleteAllInBatch(List<Owner> owners) {
        repository.deleteAllInBatch(owners);
    }

    public Optional<Owner> findByAccountIdAndClinicId(Long accountId, Long clinicId) {
        return repository.findByAccountIdAndClinicId(accountId, clinicId);
    }

    public List<Owner> findByAccountIdAndClinicIdIn(Long accountId, List<Long> clinicId) {
        return repository.findByAccountIdAndClinicIdIn(accountId, clinicId);
    }
}

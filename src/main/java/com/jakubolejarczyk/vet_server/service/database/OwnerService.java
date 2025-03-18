package com.jakubolejarczyk.vet_server.service.database;

import com.jakubolejarczyk.vet_server.model.ClinicAccount;
import com.jakubolejarczyk.vet_server.model.Owner;
import com.jakubolejarczyk.vet_server.repository.OwnerRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OwnerService {
    private final OwnerRepository repository;

    public Owner create(Long accountId, Long clinicId) {
        val owner = Owner.builder()
                .accountId(accountId)
                .clinicId(clinicId)
                .build();
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

    public List<Owner> findByAccountIdAndClinicIdIn(Long accountId, List<Long> clinicId) {
        return repository.findByAccountIdAndClinicIdIn(accountId, clinicId);
    }

    public void deleteAllInBatch(List<Owner> owners) {
        repository.deleteAllInBatch(owners);
    }
}

package com.jakubolejarczyk.vet_server.service.crud.relation;

import com.jakubolejarczyk.vet_server.model.relation.Owner;
import com.jakubolejarczyk.vet_server.repository.relation.OwnerRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

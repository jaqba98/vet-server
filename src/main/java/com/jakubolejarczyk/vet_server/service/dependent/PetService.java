package com.jakubolejarczyk.vet_server.service.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Pet;
import com.jakubolejarczyk.vet_server.repository.dependent.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PetService {
    private final PetRepository repository;

    public Pet create(Pet pet) {
        return repository.save(pet);
    }

    public Optional<Pet> findById(Long id) {
        return repository.findById(id);
    }

    public List<Pet> findAllByClientIds(List<Long> clientsIds) {
        return repository.findAllByClientIds(clientsIds);
    }

    public void updateIsArchived(List<Long> ids, Boolean isArchived) {
        repository.updateIsArchived(ids, isArchived);
    }
}

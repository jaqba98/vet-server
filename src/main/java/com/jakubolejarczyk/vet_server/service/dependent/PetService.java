package com.jakubolejarczyk.vet_server.service.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Pet;
import com.jakubolejarczyk.vet_server.repository.dependent.PetRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService extends BaseService<Pet, PetRepository> {
    public PetService(@Qualifier("petRepository") PetRepository repository) {
        super(repository);
    }

    public List<Pet> findAllByClientIdIn(List<Long> clientId) {
        return repository.findAllByClientIdIn(clientId);
    }
}

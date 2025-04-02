package com.jakubolejarczyk.vet_server.service.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Pet;
import com.jakubolejarczyk.vet_server.repository.dependent.PetRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class PetService extends BaseService<Pet> {
    public PetService(PetRepository repository) {
        super(repository);
    }
}

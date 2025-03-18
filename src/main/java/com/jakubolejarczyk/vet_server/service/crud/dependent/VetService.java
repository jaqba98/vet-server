package com.jakubolejarczyk.vet_server.service.crud.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Vet;
import com.jakubolejarczyk.vet_server.repository.dependent.VetRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VetService {
    private final VetRepository repository;

    public Vet create(Vet vet) {
        repository.save(vet);
        return vet;
    }

    public List<Vet> read() {
        return repository.findAll();
    }

    public Vet update(Vet vet) throws Exception {
        val vetById = repository.findById(vet.getId());
        if (vetById.isEmpty()) {
            throw new Exception("The update method cannot update a record that does not exist!");
        }
        repository.save(vet);
        return vet;
    }

    public Vet delete(Vet vet) {
        repository.deleteById(vet.getId());
        return vet;
    }
}

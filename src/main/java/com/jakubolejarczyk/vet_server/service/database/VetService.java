package com.jakubolejarczyk.vet_server.service.database;

import com.jakubolejarczyk.vet_server.model.Vet;
import com.jakubolejarczyk.vet_server.repository.VetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VetService {
    private final VetRepository repository;

    public void create(Long accountId, Long openingHoursId) {
        Vet vet = new Vet();
        vet.setAccountId(accountId);
        vet.setOpeningHoursId(openingHoursId);
        repository.save(vet);
    }

    public List<Vet> read() {
        return repository.findAll();
    }

    public void update(Vet vet) {
        repository.save(vet);
    }

    public void delete(Vet vet) {
        Long id = vet.getId();
        repository.deleteById(id);
    }
}

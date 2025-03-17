package com.jakubolejarczyk.vet_server.service.database;

import com.jakubolejarczyk.vet_server.model.Vet;
import com.jakubolejarczyk.vet_server.repository.VetRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VetService {
    private final VetRepository repository;

    public Vet create(Long accountId, Long openingHoursId) {
        val vet = new Vet();
        vet.setAccountId(accountId);
        vet.setOpeningHoursId(openingHoursId);
        repository.save(vet);
        return vet;
    }

    public List<Vet> read() {
        return repository.findAll();
    }

    public void update(Vet vet) {
        repository.save(vet);
    }

    public void delete(Vet vet) {
        val id = vet.getId();
        repository.deleteById(id);
    }
}

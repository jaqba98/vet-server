package com.jakubolejarczyk.vet_server.service.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Vet;
import com.jakubolejarczyk.vet_server.repository.dependent.VetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VetService {
    private final VetRepository repository;

    public Vet create(Vet vet) {
        return repository.save(vet);
    }

    public Optional<Vet> findByAccountId(Long accountId) {
        return repository.findByAccountId(accountId);
    }
}

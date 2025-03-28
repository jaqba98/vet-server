package com.jakubolejarczyk.vet_server.service.crud.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Client;
import com.jakubolejarczyk.vet_server.model.dependent.Vet;
import com.jakubolejarczyk.vet_server.repository.dependent.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository repository;

    public Client create(Client client) {
        return repository.save(client);
    }

    public Optional<Vet> findByAccountId(Long accountId) {
        return repository.findByAccountId(accountId);
    }
}

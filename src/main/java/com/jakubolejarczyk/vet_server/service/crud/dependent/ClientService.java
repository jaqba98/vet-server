package com.jakubolejarczyk.vet_server.service.crud.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Client;
import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.repository.dependent.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository repository;

    public Client create(Client client) {
        repository.save(client);
        return client;
    }

    public List<Client> read() {
        return repository.findAll();
    }

    public Client update(Client client) throws Exception {
        val clientById = repository.findById(client.getId());
        if (clientById.isEmpty()) {
            throw new Exception("The update method cannot update a record that does not exist!");
        }
        repository.save(client);
        return client;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Optional<Client> findByAccountId(Long accountId) {
        return repository.findByAccountId(accountId);
    }
}

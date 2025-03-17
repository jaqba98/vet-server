package com.jakubolejarczyk.vet_server.service.database;

import com.jakubolejarczyk.vet_server.model.Client;
import com.jakubolejarczyk.vet_server.repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository repository;

    public Client create(Long accountId) {
        val client = new Client();
        client.setAccountId(accountId);
        repository.save(client);
        return client;
    }

    public List<Client> read() {
        return repository.findAll();
    }

    public void update(Client client) {
        repository.save(client);
    }

    public void delete(Client client) {
        val id = client.getId();
        repository.deleteById(id);
    }
}

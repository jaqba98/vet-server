package com.jakubolejarczyk.vet_server.service.database;

import com.jakubolejarczyk.vet_server.model.Account;
import com.jakubolejarczyk.vet_server.model.Client;
import com.jakubolejarczyk.vet_server.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public void create(Account account) {
        Client client = new Client();
        client.setAccountId(account);
        clientRepository.save(client);
    }
}

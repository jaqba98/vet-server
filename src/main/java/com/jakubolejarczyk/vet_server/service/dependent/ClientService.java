package com.jakubolejarczyk.vet_server.service.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Client;
import com.jakubolejarczyk.vet_server.repository.dependent.ClientRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends BaseService<Client> {
    public ClientService(ClientRepository repository) {
        super(repository);
    }
}

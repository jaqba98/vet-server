package com.jakubolejarczyk.vet_server.service.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Client;
import com.jakubolejarczyk.vet_server.repository.dependent.ClientRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService extends BaseService<Client, ClientRepository> {
    public ClientService(@Qualifier("clientRepository") ClientRepository repository) {
        super(repository);
    }

    public List<Client> findAllByClinicIdIn(List<Long> clinicId) {
        return repository.findAllByClinicIdIn(clinicId);
    }

    public Optional<Client> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}

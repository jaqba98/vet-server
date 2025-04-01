package com.jakubolejarczyk.vet_server.service.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Client;
import com.jakubolejarczyk.vet_server.repository.dependent.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository repository;

    public Client create(Client client) {
        return repository.save(client);
    }

    public Optional<Client> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<Client> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Optional<Client> findByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }

    public List<Client> findAllByClinicIds(List<Long> clinicIds) {
        return repository.findAllByClinicIds(clinicIds);
    }

    public void updateIsArchived(List<Long> ids, Boolean isArchived) {
        repository.updateIsArchived(ids, isArchived);
    }
}

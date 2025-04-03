package com.jakubolejarczyk.vet_server.service.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Vet;
import com.jakubolejarczyk.vet_server.repository.dependent.VetRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VetService extends BaseService<Vet, VetRepository> {
    public VetService(@Qualifier("vetRepository") VetRepository repository) {
        super(repository);
    }

    public Optional<Vet> findByAccountId(Long accountId) {
        return repository.findByAccountId(accountId);
    }

    public List<Vet> findAllByClinicIdIn(List<Long> clinicId) {
        return repository.findAllByAccountIdIn(clinicId);
    }
}

package com.jakubolejarczyk.vet_server.service.crud.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.repository.dependent.EmploymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmploymentService {
    private final EmploymentRepository repository;

    public Employment create(Employment employment) {
        return repository.save(employment);
    }

    public List<Employment> findAllById(List<Long> ids) {
        return repository.findAllById(ids);
    }

    public List<Employment> findAllByAccountId(Long accountId) {
        return repository.findAllByAccountId(accountId);
    }

    public List<Employment> findAllByOwnerAccountId(Long accountId) {
        return repository.findAllByOwnerAccountId(accountId);
    }

    public Optional<Employment> findByClinicIdAndAccountIdAndIsOwner(Long clinicId, Long accountId) {
        return repository.findByClinicIdAndAccountIdAndIsOwner(clinicId, accountId);
    }

    public List<Employment> findAllByAccountIdAndClinicIdsInAndIsOwner(Long accountId, List<Long> clinicIds) {
        return repository.findAllByAccountIdAndClinicIdsInAndIsOwner(accountId, clinicIds);
    }

    public void updateIsArchived(List<Long> ids, Boolean isArchived) {
        repository.updateIsArchived(ids, isArchived);
    }
}

package com.jakubolejarczyk.vet_server.service.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.repository.dependent.EmploymentRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmploymentService extends BaseService<Employment, EmploymentRepository> {
    public EmploymentService(@Qualifier("employmentRepository") EmploymentRepository repository) {
        super(repository);
    }

    public Optional<Employment> findByAccountIdAndClinicIdAndIsOwner(Long accountId, Long clinicId) {
        return repository.findByAccountIdAndClinicIdAndIsOwnerTrue(accountId, clinicId);
    }

    public List<Employment> findAllByAccountIdAndClinicIdInAndIsOwner(Long accountId, List<Long> clinicId) {
        return repository.findAllByAccountIdAndClinicIdInAndIsOwnerTrue(accountId, clinicId);
    }

    public List<Employment> findAllByAccountIdAndIsOwner(Long accountId) {
        return repository.findAllByAccountIdAndIsOwnerTrue(accountId);
    }

    public List<Employment> findAllByAccountId(Long accountId) {
        return repository.findAllByAccountId(accountId);
    }
}

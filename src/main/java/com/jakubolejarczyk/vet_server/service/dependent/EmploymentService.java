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

    public Optional<Employment> findByAccountIdAndClinicId(Long accountId, Long clinicId) {
        return repository.findByAccountIdAndClinicId(accountId, clinicId);
    }

    public Optional<Employment> findByAccountIdAndClinicIdAndIsOwnerTrue(Long accountId, Long clinicId) {
        return repository.findByAccountIdAndClinicIdAndIsOwnerTrue(accountId, clinicId);
    }

    public List<Employment> findAllByClinicIdIn(List<Long> clinicId) {
        return repository.findAllByClinicIdIn(clinicId);
    }

    public List<Employment> findAllByAccountIdAndClinicIdInAndIsOwnerTrue(Long accountId, List<Long> clinicId) {
        return repository.findAllByAccountIdAndClinicIdInAndIsOwnerTrue(accountId, clinicId);
    }

    public List<Employment> findAllByAccountId(Long accountId) {
        return repository.findAllByAccountId(accountId);
    }
}

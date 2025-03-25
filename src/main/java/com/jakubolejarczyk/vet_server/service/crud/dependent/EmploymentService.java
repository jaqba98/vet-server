package com.jakubolejarczyk.vet_server.service.crud.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.repository.dependent.EmploymentRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmploymentService {
    private final EmploymentRepository repository;

    public void create(Employment employment) {
        repository.save(employment);
    }

    public List<Employment> read() {
        return repository.findAll();
    }

    public void update(Employment employment) throws Exception {
        val employmentId = employment.getId();
        val employmentById = repository.findById(employmentId);
        if (employmentById.isEmpty()) {
            throw new Exception("The update method cannot be performed! The clinic does not exist!");
        }
        repository.save(employment);
    }

    public void delete(Long employmentId) {
        repository.deleteById(employmentId);
    }

    public void deleteAll(List<Long> employmentIds) {
        repository.deleteAllByIdInBatch(employmentIds);
    }

    public Optional<Employment> findById(Long id) {
        return repository.findById(id);
    }

    public List<Employment> findAllById(List<Long> ids) {
        return repository.findAllById(ids);
    }

    public List<Employment> findByAccountIdAndClinicIdIn(Long accountId, List<Long> clinicId) {
        return repository.findByAccountIdAndClinicIdIn(accountId, clinicId);
    }

    public List<Employment> findByAccountId(Long accountId) {
        return repository.findByAccountId(accountId);
    }
}

package com.jakubolejarczyk.vet_server.service.crud.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.repository.dependent.EmploymentRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Employment> findAllByAccountId(Long accountId) {
        return repository.findAllByAccountId(accountId);
    }

    public List<Employment> findAllByAccountIdAndIsOwner(Long accountId) {
        return repository.findAllByAccountIdAndIsOwner(accountId);
    }
}

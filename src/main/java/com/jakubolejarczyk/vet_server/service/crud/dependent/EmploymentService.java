package com.jakubolejarczyk.vet_server.service.crud.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.repository.dependent.EmploymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmploymentService {
    private final EmploymentRepository repository;

    public Employment create(Employment employment) {
        return repository.save(employment);
    }
}

package com.jakubolejarczyk.vet_server.service.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.VetService;
import com.jakubolejarczyk.vet_server.repository.dependent.VetServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VetServiceService {
    private final VetServiceRepository repository;

    public VetService create(VetService vetService) {
        return repository.save(vetService);
    }

    public Optional<VetService> findById(Long id) {
        return repository.findById(id);
    }

    public List<VetService> findAllByClinicIds(List<Long> clinicIds) {
        return repository.findAllByClinicIds(clinicIds);
    }
}

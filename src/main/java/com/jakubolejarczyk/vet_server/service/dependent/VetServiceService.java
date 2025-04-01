package com.jakubolejarczyk.vet_server.service.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.VetService;
import com.jakubolejarczyk.vet_server.repository.dependent.VetServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VetServiceService {
    private final VetServiceRepository repository;

    public VetService create(VetService vetService) {
        return repository.save(vetService);
    }

    public List<VetService> findAllByClinicIds(List<Long> clinicIds) {
        return repository.findAllByClinicIds(clinicIds);
    }
}

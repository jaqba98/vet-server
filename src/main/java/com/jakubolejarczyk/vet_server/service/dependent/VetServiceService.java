package com.jakubolejarczyk.vet_server.service.dependent;

import com.jakubolejarczyk.vet_server.repository.dependent.VetServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VetServiceService {
    private final VetServiceRepository repository;
}

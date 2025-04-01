package com.jakubolejarczyk.vet_server.service.dependent;

import com.jakubolejarczyk.vet_server.repository.dependent.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PetService {
    private final PetRepository repository;
}

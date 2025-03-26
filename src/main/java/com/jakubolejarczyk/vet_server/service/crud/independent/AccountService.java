package com.jakubolejarczyk.vet_server.service.crud.independent;

import com.jakubolejarczyk.vet_server.repository.independent.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository repository;
}

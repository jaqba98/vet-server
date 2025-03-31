package com.jakubolejarczyk.vet_server.service.independent;

import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.repository.independent.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository repository;

    public Account create(Account account) {
        return repository.save(account);
    }

    public Optional<Account> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public void updateRoleByEmail(String email, String role) {
        repository.updateRoleByEmail(email, role);
    }
}

package com.jakubolejarczyk.vet_server.service.independent;

import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.repository.independent.AccountRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService extends BaseService<Account, AccountRepository> {
    public AccountService(@Qualifier("accountRepository") AccountRepository repository) {
        super(repository);
    }

    public Optional<Account> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public void updateRoleByEmail(String email, String role) {
        repository.updateRoleByEmail(email, role);
    }

    public List<Account> findAll() {
        return repository.findAll();
    }
}

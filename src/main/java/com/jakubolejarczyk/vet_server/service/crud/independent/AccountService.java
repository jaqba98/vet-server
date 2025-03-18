package com.jakubolejarczyk.vet_server.service.crud.independent;

import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.repository.independent.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository repository;

    public Account create(Account account) {
        repository.save(account);
        return account;
    }

    public List<Account> read() {
        return repository.findAll();
    }

    public Account update(Account account) throws Exception {
        val accountById = repository.findById(account.getId());
        if (accountById.isEmpty()) {
            throw new Exception("The update method cannot update a record that does not exist!");
        }
        repository.save(account);
        return account;
    }

    public Account delete(Account account) {
        repository.deleteById(account.getId());
        return account;
    }

    public Optional<Account> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public void updateRoleByEmail(String email, String role) {
        repository.updateRoleByEmail(email, role);
    }
}

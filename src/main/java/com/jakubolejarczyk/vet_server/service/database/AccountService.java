package com.jakubolejarczyk.vet_server.service.database;

import com.jakubolejarczyk.vet_server.model.Account;
import com.jakubolejarczyk.vet_server.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository repository;

    public Account create(String email, String password, String firstName, String lastName) {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setIsVerified(false);
        repository.save(account);
        return account;
    }

    public List<Account> read() {
        return repository.findAll();
    }

    public void update(Account account) {
        repository.save(account);
    }

    public void delete(Account account) {
        Long id = account.getId();
        repository.deleteById(id);
    }

    public Optional<Account> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Boolean existByEmail(String email) {
        return findByEmail(email).isPresent();
    }

    public Boolean notExistByEmail(String email) {
        return !existByEmail(email);
    }

    public void updateRole(String email, String role) {
        repository.updateRole(email, role);
    }
}

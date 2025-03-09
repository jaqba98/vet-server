package com.jakubolejarczyk.vet_server.service.database;

import com.jakubolejarczyk.vet_server.model.Account;
import com.jakubolejarczyk.vet_server.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Optional<Account> findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public Boolean existByEmail(String email) {
        return findByEmail(email).isPresent();
    }

    public Boolean notExistByEmail(String email) {
        return !existByEmail(email);
    }

    public void updateRole(String email, String password) {
    }

    public void create(String email, String password, String firstName, String lastName) {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setRole(null);
        account.setPictureUrl(null);
        account.setIsVerified(false);
        accountRepository.save(account);
    }
}

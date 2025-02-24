package com.jakubolejarczyk.vet_server.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jakubolejarczyk.vet_server.model.Account;
import com.jakubolejarczyk.vet_server.repository.AccountRepository;

@Service
public class AccountService {
    private final AccountRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(
            AccountRepository repository,
            PasswordEncoder passwordEncoder
    ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Boolean createAccount(String email, String password, String firstName, String lastName, String role) {
        Optional<Account> currentAccount = repository.getByEmail(email);
        if (currentAccount.isPresent()) {
            return false;
        }
        String hashPassword = passwordEncoder.encode(password);
        Account newAccount = new Account();
        newAccount.setEmail(email);
        newAccount.setPassword(hashPassword);
        newAccount.setFirstName(firstName);
        newAccount.setLastName(lastName);
        newAccount.setRole(role);
        newAccount.setPictureUrl("");
        newAccount.setIsVerified(false);
        repository.save(newAccount);
        return true;
    }

    public Optional<Account> getAccountByEmail(String email) {
        return repository.getByEmail(email);
    }
}

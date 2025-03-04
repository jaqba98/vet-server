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

    public Optional<Account> getAccountByEmail(String email) {
        return accountRepository.getAccountByEmail(email);
    }

    public Boolean isAccountByEmail(String email) {
        return getAccountByEmail(email).isPresent();
    }

    public Boolean isNotAccountByEmail(String email) {
        return !isAccountByEmail(email);
    }

    public void updateRole(String email, String role) {
        accountRepository.updateRole(email, role);
    }

    public void createAccount(String email, String password, String firstName, String lastName) {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setRole(null);
        account.setPictureUrl("");
        account.setIsVerified(true);
        accountRepository.save(account);
    }
}

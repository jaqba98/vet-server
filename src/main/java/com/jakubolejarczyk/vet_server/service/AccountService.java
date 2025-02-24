package com.jakubolejarczyk.vet_server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.jakubolejarczyk.vet_server.model.Account;
import com.jakubolejarczyk.vet_server.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    public Boolean isAccountByEmail(String email) {
        return accountRepository.getAccountByEmail(email).isPresent();
    }

    public void createAccount(String email, String password, String firstName, String lastName, String role) {
        String hashPassword = passwordEncoder.encode(password);
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(hashPassword);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setRole(role);
        // todo: Set the default avatar url. At the moment it is an empty string!
        account.setPictureUrl("");
        // todo: Change to false when the email confirmation will be done!
        account.setIsVerified(true);
        accountRepository.save(account);
    }
}

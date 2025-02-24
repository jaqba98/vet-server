package com.jakubolejarczyk.vet_server.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.jakubolejarczyk.vet_server.model.Account;
import com.jakubolejarczyk.vet_server.repository.AccountRepository;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    public Map<String, String> isAccountByEmail(String email) {
        Optional<Account> account = accountRepository.getByEmail(email);
        Map<String, String> errors = new HashMap<>();
        if (account.isPresent()) {
            errors.put("email", "Email already exists");
        }
        return errors;
    }

    public void createAccount(String email, String password, String firstName, String lastName, String role) {
        String hashPassword = passwordEncoder.encode(email);
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(hashPassword);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setRole(role);
        account.setPictureUrl("");
        account.setIsVerified(false);
        accountRepository.save(account);
    }
}

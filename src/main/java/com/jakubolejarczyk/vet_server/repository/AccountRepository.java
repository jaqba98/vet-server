package com.jakubolejarczyk.vet_server.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jakubolejarczyk.vet_server.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> getByEmail(String email);
}

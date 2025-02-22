package com.jakubolejarczyk.vet_server.repository;

import com.jakubolejarczyk.vet_server.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}

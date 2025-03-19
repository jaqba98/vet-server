package com.jakubolejarczyk.vet_server.repository.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByAccountId(Long accountId);
}

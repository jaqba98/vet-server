package com.jakubolejarczyk.vet_server.repository.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VetRepository extends JpaRepository<Vet, Long> {
    Optional<Vet> findByAccountId(@Param("accountId") Long accountId);
}

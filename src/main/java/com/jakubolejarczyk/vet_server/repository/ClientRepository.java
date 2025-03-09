package com.jakubolejarczyk.vet_server.repository;

import com.jakubolejarczyk.vet_server.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}

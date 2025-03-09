package com.jakubolejarczyk.vet_server.repository;

import com.jakubolejarczyk.vet_server.model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetRepository extends JpaRepository<Vet, Long> {
}

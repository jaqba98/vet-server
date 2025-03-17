package com.jakubolejarczyk.vet_server.repository;

import com.jakubolejarczyk.vet_server.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
}

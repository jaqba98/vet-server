package com.jakubolejarczyk.vet_server.repository;

import com.jakubolejarczyk.vet_server.model.Owner;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends org.springframework.data.jpa.repository.JpaRepository<Owner, Long> {
}

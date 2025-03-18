package com.jakubolejarczyk.vet_server.repository.relation;

import com.jakubolejarczyk.vet_server.model.relation.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByAccountIdAndClinicId(Long accountId, Long clinicId);
}

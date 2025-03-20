package com.jakubolejarczyk.vet_server.repository.relation;

import com.jakubolejarczyk.vet_server.model.relation.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    List<Owner> findByAccountIdAndClinicIdIn(Long accountId, List<Long> clinicId);
}

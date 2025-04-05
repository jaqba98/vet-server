package com.jakubolejarczyk.vet_server.repository.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmploymentRepository extends JpaRepository<Employment, Long> {
    Optional<Employment> findByAccountIdAndClinicId(Long accountId, Long clinicId);

    Optional<Employment> findByAccountIdAndClinicIdAndIsOwnerTrue(Long accountId, Long clinicId);

    List<Employment> findAllByAccountIdAndClinicIdInAndIsOwnerTrue(Long accountId, List<Long> clinicId);

    List<Employment> findAllByAccountId(Long accountId);
}

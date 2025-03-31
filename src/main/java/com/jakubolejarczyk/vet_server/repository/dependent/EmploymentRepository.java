package com.jakubolejarczyk.vet_server.repository.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmploymentRepository extends JpaRepository<Employment, Long> {
    @Query("SELECT e FROM Employment e WHERE e.accountId = :accountId AND e.isArchived = false")
    List<Employment> findAllByAccountId(@Param("accountId") Long accountId);

    @Query("SELECT e FROM Employment e WHERE e.clinicId = :clinicId AND e.accountId = :accountId AND e.isOwner = true AND e.isArchived = false")
    Optional<Employment> findByClinicIdAndAccountIdAndIsOwner(@Param("clinicId") Long clinicId, @Param("accountId") Long accountId);
}

package com.jakubolejarczyk.vet_server.repository.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmploymentRepository extends JpaRepository<Employment, Long> {
    @Query("SELECT e FROM Employment e WHERE e.accountId = :accountId AND e.isArchived = false")
    List<Employment> findAllByAccountId(@Param("accountId") Long accountId);

    @Query("SELECT e FROM Employment e WHERE e.accountId = :accountId AND e.isOwner = true AND e.isArchived = false")
    List<Employment> findAllByOwnerAccountId(@Param("accountId") Long accountId);

    @Query("SELECT e FROM Employment e WHERE e.clinicId = :clinicId AND e.accountId = :accountId AND e.isOwner = true AND e.isArchived = false")
    Optional<Employment> findByClinicIdAndAccountIdAndIsOwner(@Param("clinicId") Long clinicId, @Param("accountId") Long accountId);

    @Query("SELECT e FROM Employment e WHERE e.accountId = :accountId AND e.clinicId IN :clinicIds AND e.isOwner = true AND e.isArchived = false")
    List<Employment> findAllByAccountIdAndClinicIdsAndIsOwner(@Param("accountId") Long accountId, @Param("clinicIds") List<Long> clinicIds);

    @Transactional
    @Modifying
    @Query("UPDATE Employment e SET e.isArchived = :isArchived WHERE e.id IN :ids")
    void updateIsArchived(@Param("ids") List<Long> ids, @Param("isArchived") Boolean isArchived);
}

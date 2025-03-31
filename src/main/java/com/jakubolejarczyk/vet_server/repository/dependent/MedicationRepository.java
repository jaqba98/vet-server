package com.jakubolejarczyk.vet_server.repository.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Medication;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
    @Query("SELECT e FROM Medication e WHERE e.clinicId = :clinicId")
    Optional<Medication> findByClinicId(@Param("clinicId") Long clinicId);

    @Query("SELECT e FROM Medication e WHERE e.clinicId IN :clinicIds")
    List<Medication> findAllByClinicIds(@Param("clinicIds") List<Long> clinicIds);

    @Transactional
    @Modifying
    @Query("UPDATE Medication e SET e.isArchived = :isArchived WHERE e.id IN :ids")
    void updateIsArchived(@Param("ids") List<Long> ids, @Param("isArchived") Boolean isArchived);
}

package com.jakubolejarczyk.vet_server.repository.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
    @Query("SELECT e FROM Medication e WHERE e.clinicId IN :clinicIds")
    List<Medication> findAllByClinicIds(@Param("accountId") List<Long> clinicIds);
}

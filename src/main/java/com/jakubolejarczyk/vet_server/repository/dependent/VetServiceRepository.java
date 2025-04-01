package com.jakubolejarczyk.vet_server.repository.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.VetService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VetServiceRepository extends JpaRepository<VetService, Long> {
    @Query("SELECT e FROM VetService e WHERE e.clinicId IN :clinicIds AND e.isArchived = false")
    List<VetService> findAllByClinicIds(@Param("clinicIds") List<Long> clinicIds);
}

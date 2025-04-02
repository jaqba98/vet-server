package com.jakubolejarczyk.vet_server.repository.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Service;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VetServiceRepository extends JpaRepository<Service, Long> {
    @Query("SELECT e FROM VetService e WHERE e.clinicId IN :clinicIds AND e.isArchived = false")
    List<Service> findAllByClinicIds(@Param("clinicIds") List<Long> clinicIds);

    @Transactional
    @Modifying
    @Query("UPDATE VetService e SET e.isArchived = :isArchived WHERE e.id IN :ids")
    void updateIsArchived(@Param("ids") List<Long> ids, @Param("isArchived") Boolean isArchived);
}

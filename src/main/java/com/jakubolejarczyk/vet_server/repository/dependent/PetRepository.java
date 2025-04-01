package com.jakubolejarczyk.vet_server.repository.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Pet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query("SELECT e FROM Pet e WHERE e.clientId IN :clientIds AND e.isArchived = false")
    List<Pet> findAllByClientIds(@Param("clientIds") List<Long> clientIds);

    @Transactional
    @Modifying
    @Query("UPDATE Pet e SET e.isArchived = :isArchived WHERE e.id IN :ids")
    void updateIsArchived(@Param("ids") List<Long> ids, @Param("isArchived") Boolean isArchived);
}

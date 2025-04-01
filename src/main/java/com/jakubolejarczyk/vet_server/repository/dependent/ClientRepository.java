package com.jakubolejarczyk.vet_server.repository.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Client;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(@Param("email") String email);

    Optional<Client> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("SELECT e FROM Client e WHERE e.clinicId IN :clinicIds AND e.isArchived = false")
    List<Client> findAllByClinicIds(@Param("clinicIds") List<Long> clinicIds);

    @Transactional
    @Modifying
    @Query("UPDATE Client e SET e.isArchived = :isArchived WHERE e.id IN :ids")
    void updateIsArchived(@Param("ids") List<Long> ids, @Param("isArchived") Boolean isArchived);
}

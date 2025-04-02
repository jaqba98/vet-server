package com.jakubolejarczyk.vet_server.repository.independent;

import com.jakubolejarczyk.vet_server.model.independent.OpeningHour;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpeningHoursRepository extends JpaRepository<OpeningHour, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE OpeningHours e SET e.isArchived = :isArchived WHERE e.id IN :ids")
    void updateIsArchived(@Param("ids") List<Long> ids, @Param("isArchived") Boolean isArchived);
}

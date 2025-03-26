package com.jakubolejarczyk.vet_server.repository.independent;

import com.jakubolejarczyk.vet_server.model.independent.OpeningHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpeningHoursRepository extends JpaRepository<OpeningHours, Long> {
    @Query("SELECT c FROM OpeningHours c WHERE c.id IN :ids AND c.isArchived = false")
    List<OpeningHours> findAllByIds(List<Long> ids);
}

package com.jakubolejarczyk.vet_server.repository;

import com.jakubolejarczyk.vet_server.model.OpeningHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpeningHoursRepository extends JpaRepository<OpeningHours, Long> {
}

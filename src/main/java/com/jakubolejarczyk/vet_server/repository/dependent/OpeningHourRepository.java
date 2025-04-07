package com.jakubolejarczyk.vet_server.repository.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.OpeningHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OpeningHourRepository extends JpaRepository<OpeningHour, Long> {
    List<OpeningHour> findAllByClinicIdIn(List<Long> clinicId);

    Optional<OpeningHour> findByClinicId(Long clinicId);
}

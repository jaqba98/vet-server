package com.jakubolejarczyk.vet_server.repository.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.ServiceClinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceClinicRepository extends JpaRepository<ServiceClinic, Long> {
    List<ServiceClinic> findAllByClinicIdIn(List<Long> clinicId);
}

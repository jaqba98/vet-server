package com.jakubolejarczyk.vet_server.repository.independent;

import com.jakubolejarczyk.vet_server.model.independent.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    public Optional<Clinic> findByFullName(String fullName);

    public Optional<Clinic> findByEmail(String email);
}

package com.jakubolejarczyk.vet_server.repository;

import com.jakubolejarczyk.vet_server.model.ClinicAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicAccountRepository extends JpaRepository<ClinicAccount, Long> {
}

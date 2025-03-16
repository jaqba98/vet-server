package com.jakubolejarczyk.vet_server.repository;

import com.jakubolejarczyk.vet_server.model.ClinicAccount;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicAccountRepository extends org.springframework.data.jpa.repository.JpaRepository<ClinicAccount, Long> {
}

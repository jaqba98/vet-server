package com.jakubolejarczyk.vet_server.repository;

import com.jakubolejarczyk.vet_server.model.ClinicAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClinicAccountRepository extends JpaRepository<ClinicAccount, Long> {
    List<ClinicAccount> findByAccountId(Long accountId);

    Optional<ClinicAccount> findByAccountIdAndClinicId(Long accountId, Long clinicId);

    List<ClinicAccount> findByAccountIdAndClinicIdIn(Long accountId, List<Long> clinicId);
}

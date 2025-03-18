package com.jakubolejarczyk.vet_server.repository.relation;

import com.jakubolejarczyk.vet_server.model.relation.ClinicAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClinicAccountRepository extends JpaRepository<ClinicAccount, Long> {
    List<ClinicAccount> findAllByAccountId(Long accountId);

    Optional<ClinicAccount> findByAccountIdAndClinicId(Long accountId, Long clinicId);
}

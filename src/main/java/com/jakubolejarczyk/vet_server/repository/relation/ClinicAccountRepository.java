package com.jakubolejarczyk.vet_server.repository.relation;

import com.jakubolejarczyk.vet_server.model.relation.ClinicAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicAccountRepository extends JpaRepository<ClinicAccount, Long> {
    List<ClinicAccount> findByAccountIdAndClinicIdIn(Long accountId, List<Long> clinicId);

    List<ClinicAccount> findByAccountId(Long accountId);
}

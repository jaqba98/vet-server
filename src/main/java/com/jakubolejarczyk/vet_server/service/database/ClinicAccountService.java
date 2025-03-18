package com.jakubolejarczyk.vet_server.service.database;

import com.jakubolejarczyk.vet_server.model.ClinicAccount;
import com.jakubolejarczyk.vet_server.repository.ClinicAccountRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClinicAccountService {
    private final ClinicAccountRepository repository;

    public ClinicAccount create(Long accountId, Long clinicId) {
        val clinicAccount = ClinicAccount.builder()
                .accountId(accountId)
                .clinicId(clinicId)
                .build();
        repository.save(clinicAccount);
        return clinicAccount;
    }

    public List<ClinicAccount> read() {
        return repository.findAll();
    }

    public void update(ClinicAccount clinicAccount) {
        repository.save(clinicAccount);
    }

    public void delete(ClinicAccount clinicAccount) {
        val id = clinicAccount.getId();
        repository.deleteById(id);
    }

    public List<ClinicAccount> findByAccountId(Long accountId) {
        return repository.findByAccountId(accountId);
    }

    public Optional<ClinicAccount> findByAccountIdAndClinicId(Long accountId, Long clinicId) {
        return repository.findByAccountIdAndClinicId(accountId, clinicId);
    }

    public List<ClinicAccount> findByAccountIdAndClinicIdIn(Long accountId, List<Long> clinicId) {
        return repository.findByAccountIdAndClinicIdIn(accountId, clinicId);
    }

    public void deleteAllInBatch(List<ClinicAccount> clinicAccounts) {
        repository.deleteAllInBatch(clinicAccounts);
    }
}

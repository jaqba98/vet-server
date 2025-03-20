package com.jakubolejarczyk.vet_server.service.crud.relation;

import com.jakubolejarczyk.vet_server.model.relation.ClinicAccount;
import com.jakubolejarczyk.vet_server.repository.relation.ClinicAccountRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClinicAccountService {
    private final ClinicAccountRepository repository;

    public ClinicAccount create(ClinicAccount clinicAccount) {
        repository.save(clinicAccount);
        return clinicAccount;
    }

    public List<ClinicAccount> read() {
        return repository.findAll();
    }

    public ClinicAccount update(ClinicAccount clinicAccount) throws Exception {
        val clinicAccountById = repository.findById(clinicAccount.getId());
        if (clinicAccountById.isEmpty()) {
            throw new Exception("The update method cannot update a record that does not exist!");
        }
        repository.save(clinicAccount);
        return clinicAccount;
    }

    public ClinicAccount delete(ClinicAccount clinicAccount) {
        repository.deleteById(clinicAccount.getId());
        return clinicAccount;
    }

    public void deleteAllInBatch(List<ClinicAccount> clinicAccounts) {
        repository.deleteAllInBatch(clinicAccounts);
    }

    public List<ClinicAccount> findByAccountIdAndClinicIdIn(Long accountId, List<Long> clinicId) {
        return repository.findByAccountIdAndClinicIdIn(accountId, clinicId);
    }

    public List<ClinicAccount> findByAccountId(Long accountId) {
        return repository.findByAccountId(accountId);
    }
}

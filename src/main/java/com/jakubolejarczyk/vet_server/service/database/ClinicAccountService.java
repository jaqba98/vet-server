package com.jakubolejarczyk.vet_server.service.database;

import com.jakubolejarczyk.vet_server.model.ClinicAccount;
import com.jakubolejarczyk.vet_server.repository.ClinicAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClinicAccountService {
    private final ClinicAccountRepository repository;

    public void create(Long accountId, Long clinicId) {
        ClinicAccount clinicAccount = new ClinicAccount();
        clinicAccount.setAccountId(accountId);
        clinicAccount.setClinicId(clinicId);
        repository.save(clinicAccount);
    }

    public List<ClinicAccount> read() {
        return repository.findAll();
    }

    public void update(ClinicAccount clinicAccount) {
        repository.save(clinicAccount);
    }

    public void delete(ClinicAccount clinicAccount) {
        Long id = clinicAccount.getId();
        repository.deleteById(id);
    }
}

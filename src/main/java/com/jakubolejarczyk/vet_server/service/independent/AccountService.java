package com.jakubolejarczyk.vet_server.service.independent;

import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.repository.independent.AccountRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends BaseService<Account> {
    public AccountService(AccountRepository repository) {
        super(repository);
    }
}

package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.model.independent.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IsRoleStep {
    public Boolean runStep(Account account, String role) {
        return account.getRole().contains(role);
    }
}

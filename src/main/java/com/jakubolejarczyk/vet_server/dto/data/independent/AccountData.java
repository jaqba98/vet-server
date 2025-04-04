package com.jakubolejarczyk.vet_server.dto.data.independent;

import com.jakubolejarczyk.vet_server.model.independent.Account;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AccountData {
    private List<Account> accounts;
}

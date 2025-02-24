package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jakubolejarczyk.vet_server.service.AccountService;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public void create(@RequestBody Account account) {
//        service.createAccount(account);
    }
}

package com.jakubolejarczyk.vet_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void save() {
        this.contactRepository.save(new Contact("aaa", "bbb", true));
    }
}

package com.jakubolejarczyk.vet_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clinic")
public class ClinicController {
    private final ContactService contactService;

    @Autowired
    public ClinicController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/get-clinics")
    public String getClinics() {
        this.contactService.save();
        return "Hello Clinics!";
    }
}

package com.jakubolejarczyk.vet_server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clinic")
public class ClinicController {
    @GetMapping("/get-clinics")
    public String getClinics() {
        return "Hello Clinics!";
    }
}

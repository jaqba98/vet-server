package com.jakubolejarczyk.vet_server;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class DogController {
    @GetMapping("/dog")
    String getDog() {
        return "Dog";
    }
}

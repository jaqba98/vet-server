package com.olejarczykjakub.vet_server;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HelloController {
    @GetMapping("/hello-world")
    public Map<String, String> hello() {
        return Map.of("message", "Hello World!");
    }
}

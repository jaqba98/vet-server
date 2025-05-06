package com.olejarczykjakub.vet_server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello-world")
    public String hello() {
        return "Hello World!";
    }
}

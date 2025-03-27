package com.jakubolejarczyk.vet_server.service.input;

public record CreateAccountInput(String email, String password, String firstName, String lastName) {
}

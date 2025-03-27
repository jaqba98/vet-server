package com.jakubolejarczyk.vet_server.service.input;

public record GetTokenByLoginDetailsInput(String email, String password) {
}

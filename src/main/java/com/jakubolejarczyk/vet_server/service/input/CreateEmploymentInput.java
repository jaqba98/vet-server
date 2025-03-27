package com.jakubolejarczyk.vet_server.service.input;

public record CreateEmploymentInput(Boolean isOwner, Long accountId, Long clinicId) {
}

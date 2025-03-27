package com.jakubolejarczyk.vet_server.service.input.create;

public record CreateEmploymentInput(Boolean isOwner, Long accountId, Long clinicId) {
}

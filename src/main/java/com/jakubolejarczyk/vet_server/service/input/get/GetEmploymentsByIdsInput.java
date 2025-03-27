package com.jakubolejarczyk.vet_server.service.input.get;

import java.util.List;

public record GetEmploymentsByIdsInput(List<Long> employmentsIds) {
}

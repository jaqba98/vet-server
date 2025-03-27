package com.jakubolejarczyk.vet_server.service.input;

import java.util.List;

public record GetClinicsByIdsInput(List<Long> clinicsIds) {
}

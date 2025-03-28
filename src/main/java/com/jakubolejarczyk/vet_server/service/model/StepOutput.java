package com.jakubolejarczyk.vet_server.service.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StepOutput {
    private Boolean success;
    private String message;
}

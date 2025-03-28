package com.jakubolejarczyk.vet_server.service.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StepOutput<TOutput> {
    private Boolean success;
    private String message;
    private TOutput output;
}

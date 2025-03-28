package com.jakubolejarczyk.vet_server.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StepOutput<TOutput> {
    private Boolean success;
    private String message;
    private TOutput output;
}

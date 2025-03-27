package com.jakubolejarczyk.vet_server.service.output;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class StepOutput<TData> {
    private Boolean success;

    private String message;

    private TData data;
}

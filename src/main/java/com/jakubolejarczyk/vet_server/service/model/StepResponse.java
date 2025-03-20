package com.jakubolejarczyk.vet_server.service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class StepResponse<TData> {
    Boolean success;
    TData data;
}

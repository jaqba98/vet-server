package com.jakubolejarczyk.vet_server.service.model;

public interface StepModel<TInput, TOutput> {
    StepOutput<TOutput> runStep(TInput input);
}

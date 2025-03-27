package com.jakubolejarczyk.vet_server.service.model;

public interface StepModel<TData, TResponse> {
    StepResponse<TResponse> runStep(TData data);
}

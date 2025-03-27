package com.jakubolejarczyk.vet_server.service.model;

import com.jakubolejarczyk.vet_server.service.output.StepOutput;

public interface StepModel<TInput, TOutput> {
    StepOutput<TOutput> runStep(TInput input);
}

package com.jakubolejarczyk.vet_server.step.model;

import com.jakubolejarczyk.vet_server.store.StepStore;

public interface StepModel {
    void runStep(StepStore stepStore);
}

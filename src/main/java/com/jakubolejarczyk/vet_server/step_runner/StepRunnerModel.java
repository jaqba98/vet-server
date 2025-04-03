package com.jakubolejarczyk.vet_server.step_runner;

import com.jakubolejarczyk.vet_server.store.StepStore;

public interface StepRunnerModel {
    void runStep(StepStore stepStore);
}

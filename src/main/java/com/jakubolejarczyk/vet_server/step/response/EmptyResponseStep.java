package com.jakubolejarczyk.vet_server.step.response;

import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmptyResponseStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
    }
}

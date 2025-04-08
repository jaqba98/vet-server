package com.jakubolejarczyk.vet_server.step.response.dependent.appointment;

import com.jakubolejarczyk.vet_server.dto.data.dependent.AppointmentData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.AppointmentMetadata;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppointmentDeleteResponseStep implements StepRunnerModel<AppointmentData, AppointmentMetadata> {
    @Override
    public void runStep(StepStore<AppointmentData, AppointmentMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            stepStore.addMessage("Appointments were deleted correctly!");
        }
    }
}


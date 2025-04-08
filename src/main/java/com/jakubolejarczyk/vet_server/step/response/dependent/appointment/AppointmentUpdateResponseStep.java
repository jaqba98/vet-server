package com.jakubolejarczyk.vet_server.step.response.dependent.appointment;

import com.jakubolejarczyk.vet_server.dto.data.dependent.AppointmentData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.AppointmentMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.Appointment;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class AppointmentUpdateResponseStep implements StepRunnerModel<AppointmentData, AppointmentMetadata> {
    @Override
    public void runStep(StepStore<AppointmentData, AppointmentMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            if (stepStore.hasNotItem("appointmentData")) throw new Error("The appointmentData is required!");
            val appointmentData = stepStore.getItem("appointmentData", Appointment.class);
            val data = new AppointmentData(Collections.singletonList(appointmentData));
            stepStore.addMessage("Appointments were updated correctly!");
            stepStore.setData(data);
        } else {
            if (stepStore.hasNotItem("appointmentRequest")) throw new Error("The appointmentRequest is required!");
            val appointmentRequest = stepStore.getItem("appointmentRequest", Appointment.class);
            val data = new AppointmentData(Collections.singletonList(appointmentRequest));
            stepStore.setData(data);
        }
    }
}

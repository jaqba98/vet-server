package com.jakubolejarczyk.vet_server.step.response.dependent.appointment;

import com.jakubolejarczyk.vet_server.dto.metadata.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.dto.data.dependent.AppointmentData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.AppointmentMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.Appointment;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppointmentReadResponseStep implements StepRunnerModel<AppointmentData, AppointmentMetadata> {
    @Override
    public void runStep(StepStore<AppointmentData, AppointmentMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            if (stepStore.hasNotItem("clinicsData")) throw new Error("The clinicsData is required!");
            if (stepStore.hasNotItem("clinicId")) throw new Error("The clinicId is required!");
            if (stepStore.hasNotItem("petId")) throw new Error("The petId is required!");
            if (stepStore.hasNotItem("vetId")) throw new Error("The vetId is required!");
            val appointmentsData = stepStore.getItemAsArray("appointmentsData", Appointment.class);
            val clinicId = stepStore.getItem("clinicId", BaseMetadata.class);
            val petId = stepStore.getItem("petId", BaseMetadata.class);
            val vetId = stepStore.getItem("vetId", BaseMetadata.class);
            val data = new AppointmentData(appointmentsData);
            val metadata = new AppointmentMetadata(clinicId, petId, vetId);
            stepStore.addMessage("Appointments were read correctly!");
            stepStore.setData(data);
            stepStore.setMetadata(metadata);
        }
    }
}


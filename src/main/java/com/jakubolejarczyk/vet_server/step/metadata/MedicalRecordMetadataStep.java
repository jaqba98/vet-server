package com.jakubolejarczyk.vet_server.step.metadata;

import com.jakubolejarczyk.vet_server.dto.metadata.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.dto.data.dependent.MedicalRecordData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.MedicalRecordMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.Appointment;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MedicalRecordMetadataStep implements StepRunnerModel<MedicalRecordData, MedicalRecordMetadata> {
    @Override
    public void runStep(StepStore<MedicalRecordData, MedicalRecordMetadata> stepStore) {
        if (stepStore.hasNotItem("appointmentsData")) throw new Error("The appointmentsData is required!");
        val appointmentId = new BaseMetadata();
        stepStore.getItemAsArray("appointmentsData", Appointment.class).forEach(appointment -> appointmentId.addValue(appointment.getId(), appointment.getFullName()));
        stepStore.setItem("appointmentId", appointmentId);
    }
}

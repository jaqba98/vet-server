package com.jakubolejarczyk.vet_server.step.create;

import com.jakubolejarczyk.vet_server.model.dependent.Appointment;
import com.jakubolejarczyk.vet_server.model.dependent.MedicalRecord;
import com.jakubolejarczyk.vet_server.service.dependent.MedicalRecordService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateMedicalRecordStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final MedicalRecordService medicalRecordService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("appointmentData")) throw new Error("The appointmentData is required!");
        val appointmentData = stepStore.getItem("appointmentData", Appointment.class);
        val medicalRecord = MedicalRecord.builder()
            .appointmentId(appointmentData.getId())
            .build();
        val medicalRecordData = medicalRecordService.save(medicalRecord);
        stepStore.setItem("medicalRecordData", medicalRecordData);
    }
}

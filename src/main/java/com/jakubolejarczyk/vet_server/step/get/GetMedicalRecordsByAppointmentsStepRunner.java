package com.jakubolejarczyk.vet_server.step.get;

import com.jakubolejarczyk.vet_server.model.dependent.Appointment;
import com.jakubolejarczyk.vet_server.service.independent.MedicalRecordService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetMedicalRecordsByAppointmentsStepRunner implements StepRunnerModel {
    private final MedicalRecordService medicalRecordService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("appointments")) throw new Error("The appointments is required!");
        val appointments = stepStore.getItemAsArray("appointments", Appointment.class);
        val medicalRecordsIds = appointments.stream().map(Appointment::getMedicalRecordId).toList();
        val medicalRecords = medicalRecordService.findAllById(medicalRecordsIds);
        stepStore.setItem("medicalRecords", medicalRecords);
    }
}

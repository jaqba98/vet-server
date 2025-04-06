package com.jakubolejarczyk.vet_server.step.get.medical_record;

import com.jakubolejarczyk.vet_server.model.dependent.Appointment;
import com.jakubolejarczyk.vet_server.service.independent.MedicalRecordService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetMedicalRecordsByAppointmentsStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final MedicalRecordService medicalRecordService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("appointmentsData")) throw new Error("The appointmentsData is required!");
        val appointmentsData = stepStore.getItemAsArray("appointmentsData", Appointment.class);
        val medicalRecordsIds = appointmentsData.stream()
            .map(Appointment::getMedicalRecordId)
            .toList();
        val medicalRecordsData = medicalRecordService.findAllById(medicalRecordsIds);
        // Data
        stepStore.setItem("medicalRecordsData", medicalRecordsData);
    }
}

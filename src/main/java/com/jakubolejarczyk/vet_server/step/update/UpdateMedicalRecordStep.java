package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.model.dependent.MedicalRecord;
import com.jakubolejarczyk.vet_server.service.dependent.MedicalRecordService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateMedicalRecordStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final MedicalRecordService medicalRecordService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("medicalRecordRequest")) throw new Error("The medicalRecordRequest is required!");
        val medicalRecordRequest = stepStore.getItem("medicalRecordRequest", MedicalRecord.class);
        val medicalRecordId = medicalRecordRequest.getId();
        val currentMedicalRecord = medicalRecordService.findById(medicalRecordId);
        if (currentMedicalRecord.isEmpty()) {
            stepStore.setSuccess(false);
            stepStore.addMessage("Failed to update medical record!");
            return;
        }
        currentMedicalRecord.get().setDiagnosis(medicalRecordRequest.getDiagnosis());
        currentMedicalRecord.get().setTreatment(medicalRecordRequest.getTreatment());
        currentMedicalRecord.get().setProcedures(medicalRecordRequest.getProcedures());
        currentMedicalRecord.get().setNextAppointment(medicalRecordRequest.getNextAppointment());
        currentMedicalRecord.get().setStatus(medicalRecordRequest.getStatus());
        currentMedicalRecord.get().setNotes(medicalRecordRequest.getNotes());
        currentMedicalRecord.get().setAppointmentId(medicalRecordRequest.getAppointmentId());
        val medicalRecordData = medicalRecordService.save(currentMedicalRecord.get());
        stepStore.setItem("medicalRecordData", medicalRecordData);
    }
}

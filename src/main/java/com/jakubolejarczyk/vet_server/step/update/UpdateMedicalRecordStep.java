package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.model.independent.MedicalRecord;
import com.jakubolejarczyk.vet_server.service.independent.MedicalRecordService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateMedicalRecordStep implements StepModel {
    private final MedicalRecordService medicalRecordService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("requestMedicalRecord")) throw new Error("The requestMedicalRecord is required!");
        val requestMedicalRecord = stepStore.getItem("requestMedicalRecord", MedicalRecord.class);
        val medicalRecordId = requestMedicalRecord.getId();
        val currentMedicalRecord = medicalRecordService.findById(medicalRecordId);
        if (currentMedicalRecord.isPresent()) {
            val newMedicalRecord = MedicalRecord.builder()
                    .id(currentMedicalRecord.get().getId())
                    .isArchived(currentMedicalRecord.get().getIsArchived())
                    .diagnosis(requestMedicalRecord.getDiagnosis())
                    .treatment(requestMedicalRecord.getTreatment())
                    .procedures(requestMedicalRecord.getProcedures())
                    .nextAppointment(requestMedicalRecord.getNextAppointment())
                    .status(requestMedicalRecord.getStatus())
                    .notes(requestMedicalRecord.getNotes())
                    .build();
            val medicalRecord = medicalRecordService.create(newMedicalRecord);
            stepStore.setItem("medicalRecord", medicalRecord);
            return;
        }
        stepStore.setSuccess(false);
        stepStore.addMessage("Failed to update an medical record.");
    }
}

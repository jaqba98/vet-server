package com.jakubolejarczyk.vet_server.step.response.dependent.medical_record;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.dto.data.independent.MedicalRecordData;
import com.jakubolejarczyk.vet_server.dto.metadata.independent.MedicalRecordMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.MedicalRecord;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MedicalRecordReadResponseStep implements StepRunnerModel<MedicalRecordData, MedicalRecordMetadata> {
    @Override
    public void runStep(StepStore<MedicalRecordData, MedicalRecordMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            if (stepStore.hasNotItem("medicalRecordsData")) throw new Error("The medicalRecordsData is required!");
            if (stepStore.hasNotItem("appointmentId")) throw new Error("The appointmentId is required!");
            val medicalRecordsData = stepStore.getItemAsArray("medicalRecordsData", MedicalRecord.class);
            val appointmentId = stepStore.getItem("appointmentId", BaseMetadata.class);
            val data = new MedicalRecordData(medicalRecordsData);
            val metadata = new MedicalRecordMetadata(appointmentId);
            stepStore.addMessage("Medical records were read correctly!");
            stepStore.setData(data);
            stepStore.setMetadata(metadata);
        }
    }
}

package com.jakubolejarczyk.vet_server.step.response.dependent.medical_record;

import com.jakubolejarczyk.vet_server.dto.data.independent.MedicalRecordData;
import com.jakubolejarczyk.vet_server.dto.metadata.independent.MedicalRecordMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.MedicalRecord;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class MedicalRecordUpdateResponseStep implements StepRunnerModel<MedicalRecordData, MedicalRecordMetadata> {
    @Override
    public void runStep(StepStore<MedicalRecordData, MedicalRecordMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            if (stepStore.hasNotItem("medicalRecordData")) throw new Error("The medicalRecordData is required!");
            val medicalRecordData = stepStore.getItem("medicalRecordData", MedicalRecord.class);
            val data = new MedicalRecordData(Collections.singletonList(medicalRecordData));
            stepStore.addMessage("Medical record was updated correctly!");
            stepStore.setData(data);
        } else {
            if (stepStore.hasNotItem("medicalRecordRequest")) throw new Error("The medicalRecordRequest is required!");
            val medicalRecordRequest = stepStore.getItem("medicalRecordRequest", MedicalRecord.class);
            val data = new MedicalRecordData(Collections.singletonList(medicalRecordRequest));
            stepStore.setData(data);
        }
    }
}

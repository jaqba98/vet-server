package com.jakubolejarczyk.vet_server.dto.data.independent;

import com.jakubolejarczyk.vet_server.model.independent.MedicalRecord;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MedicalRecordData {
    private List<MedicalRecord> medicalRecords;
}

package com.jakubolejarczyk.vet_server.service.independent;

import com.jakubolejarczyk.vet_server.model.independent.MedicalRecord;
import com.jakubolejarczyk.vet_server.repository.independent.MedicalRecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MedicalRecordService {
    private final MedicalRecordRepository repository;

    public MedicalRecord create(MedicalRecord medicalRecord) {
        return repository.save(medicalRecord);
    }
}

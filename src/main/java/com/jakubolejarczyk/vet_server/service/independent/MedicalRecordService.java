package com.jakubolejarczyk.vet_server.service.independent;

import com.jakubolejarczyk.vet_server.model.independent.MedicalRecord;
import com.jakubolejarczyk.vet_server.repository.independent.MedicalRecordRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService extends BaseService<MedicalRecord> {
    public MedicalRecordService(MedicalRecordRepository repository) {
        super(repository);
    }
}

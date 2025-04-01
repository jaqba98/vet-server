package com.jakubolejarczyk.vet_server.service.independent;

import com.jakubolejarczyk.vet_server.model.independent.MedicalRecord;
import com.jakubolejarczyk.vet_server.repository.independent.MedicalRecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MedicalRecordService {
    private final MedicalRecordRepository repository;

    public MedicalRecord create(MedicalRecord medicalRecord) {
        return repository.save(medicalRecord);
    }

    public Optional<MedicalRecord> findById(Long id) {
        return repository.findById(id);
    }

    public List<MedicalRecord> findAllById(List<Long> ids) {
        return repository.findAllById(ids);
    }

    public void updateIsArchived(List<Long> ids, Boolean isArchived) {
        repository.updateIsArchived(ids, isArchived);
    }
}

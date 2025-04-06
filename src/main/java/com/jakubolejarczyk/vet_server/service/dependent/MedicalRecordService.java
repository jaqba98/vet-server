package com.jakubolejarczyk.vet_server.service.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.MedicalRecord;
import com.jakubolejarczyk.vet_server.repository.dependent.MedicalRecordRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService extends BaseService<MedicalRecord, MedicalRecordRepository> {
    public MedicalRecordService(@Qualifier("medicalRecordRepository") MedicalRecordRepository repository) {
        super(repository);
    }

    public List<MedicalRecord> findAllByAppointmentIdIn(List<Long> appointmentId) {
        return repository.findAllByAppointmentIdIn(appointmentId);
    }
}

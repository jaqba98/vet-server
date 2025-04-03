package com.jakubolejarczyk.vet_server.service.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.repository.dependent.ClinicRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ClinicService extends BaseService<Clinic, ClinicRepository> {
    public ClinicService(@Qualifier("clinicRepository") ClinicRepository repository) {
        super(repository);
    }
}

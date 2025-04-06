package com.jakubolejarczyk.vet_server.service.independent;

import com.jakubolejarczyk.vet_server.model.independent.Clinic;
import com.jakubolejarczyk.vet_server.repository.independent.ClinicRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ClinicService extends BaseService<Clinic, ClinicRepository> {
    public ClinicService(@Qualifier("clinicRepository") ClinicRepository repository) {
        super(repository);
    }
}

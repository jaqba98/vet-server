package com.jakubolejarczyk.vet_server.service.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.ServiceClinic;
import com.jakubolejarczyk.vet_server.repository.dependent.ServiceClinicRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ServiceClinicService extends BaseService<ServiceClinic> {
    public ServiceClinicService(ServiceClinicRepository repository) {
        super(repository);
    }
}

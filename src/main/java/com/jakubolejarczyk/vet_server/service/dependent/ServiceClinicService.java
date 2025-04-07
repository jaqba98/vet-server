package com.jakubolejarczyk.vet_server.service.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.ServiceClinic;
import com.jakubolejarczyk.vet_server.repository.dependent.ServiceClinicRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceClinicService extends BaseService<ServiceClinic, ServiceClinicRepository> {
    public ServiceClinicService(@Qualifier("serviceClinicRepository") ServiceClinicRepository repository) {
        super(repository);
    }

    public List<ServiceClinic> findAllByClinicIdIn(List<Long> clinicId) {
        return repository.findAllByClinicIdIn(clinicId);
    }
}

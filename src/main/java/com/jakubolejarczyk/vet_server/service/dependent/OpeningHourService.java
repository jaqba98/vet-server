package com.jakubolejarczyk.vet_server.service.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.OpeningHour;
import com.jakubolejarczyk.vet_server.repository.dependent.OpeningHourRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpeningHourService extends BaseService<OpeningHour, OpeningHourRepository> {
    public OpeningHourService(@Qualifier("openingHourRepository") OpeningHourRepository repository) {
        super(repository);
    }

    public List<OpeningHour> findAllByClinicIdIn(List<Long> clinicId) {
        return repository.findAllByClinicIdIn(clinicId);
    }
}

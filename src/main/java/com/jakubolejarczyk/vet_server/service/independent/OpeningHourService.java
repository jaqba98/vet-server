package com.jakubolejarczyk.vet_server.service.independent;

import com.jakubolejarczyk.vet_server.model.independent.OpeningHour;
import com.jakubolejarczyk.vet_server.repository.independent.OpeningHourRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OpeningHourService extends BaseService<OpeningHour, OpeningHourRepository> {
    public OpeningHourService(@Qualifier("openingHourRepository") OpeningHourRepository repository) {
        super(repository);
    }
}

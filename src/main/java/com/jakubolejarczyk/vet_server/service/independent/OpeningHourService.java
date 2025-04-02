package com.jakubolejarczyk.vet_server.service.independent;

import com.jakubolejarczyk.vet_server.model.independent.OpeningHour;
import com.jakubolejarczyk.vet_server.repository.independent.OpeningHourRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class OpeningHourService extends BaseService<OpeningHour> {
    public OpeningHourService(OpeningHourRepository repository) {
        super(repository);
    }
}

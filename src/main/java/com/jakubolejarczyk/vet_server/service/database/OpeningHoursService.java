package com.jakubolejarczyk.vet_server.service.database;

import com.jakubolejarczyk.vet_server.repository.OpeningHoursRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OpeningHoursService {
    private final OpeningHoursRepository openingHoursRepository;

    public void create() {
//        OpeningHours openingHours = new OpeningHours();
//        openingHoursRepository.save(openingHours);
//        return openingHours;
    }
}

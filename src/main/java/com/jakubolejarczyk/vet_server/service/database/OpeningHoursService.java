package com.jakubolejarczyk.vet_server.service.database;

import com.jakubolejarczyk.vet_server.model.OpeningHours;
import com.jakubolejarczyk.vet_server.repository.OpeningHoursRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OpeningHoursService {
    private final OpeningHoursRepository repository;

    public void create() {
        OpeningHours openingHours = new OpeningHours();
        repository.save(openingHours);
    }

    public List<OpeningHours> read() {
        return repository.findAll();
    }

    public void update(OpeningHours openingHours) {
        repository.save(openingHours);
    }

    public void delete(OpeningHours openingHours) {
        Long id = openingHours.getId();
        repository.deleteById(id);
    }
}

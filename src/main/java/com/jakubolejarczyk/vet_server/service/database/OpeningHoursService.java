package com.jakubolejarczyk.vet_server.service.database;

import com.jakubolejarczyk.vet_server.model.OpeningHours;
import com.jakubolejarczyk.vet_server.repository.OpeningHoursRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OpeningHoursService {
    private final OpeningHoursRepository repository;

    public OpeningHours create() {
        val openingHours = OpeningHours.builder().build();
        repository.save(openingHours);
        return openingHours;
    }

    public List<OpeningHours> read() {
        return repository.findAll();
    }

    public void update(OpeningHours openingHours) {
        repository.save(openingHours);
    }

    public void delete(OpeningHours openingHours) {
        val id = openingHours.getId();
        repository.deleteById(id);
    }

    public void deleteAllByIdInBatch(List<Long> ids) {
        repository.deleteAllByIdInBatch(ids);
    }
}

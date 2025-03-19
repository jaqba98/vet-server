package com.jakubolejarczyk.vet_server.service.crud.independent;

import com.jakubolejarczyk.vet_server.model.independent.OpeningHours;
import com.jakubolejarczyk.vet_server.repository.independent.OpeningHoursRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OpeningHoursService {
    private final OpeningHoursRepository repository;

    public OpeningHours create(OpeningHours openingHours) {
        repository.save(openingHours);
        return openingHours;
    }

    public List<OpeningHours> read() {
        return repository.findAll();
    }

    public OpeningHours update(OpeningHours openingHours) throws Exception {
        val openingHoursById = repository.findById(openingHours.getId());
        if (openingHoursById.isEmpty()) {
            throw new Exception("The update method cannot update a record that does not exist!");
        }
        repository.save(openingHours);
        return openingHours;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void deleteAllByIdInBatch(List<Long> ids) {
        repository.deleteAllByIdInBatch(ids);
    }
}

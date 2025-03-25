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

    public void create(OpeningHours openingHours) {
        repository.save(openingHours);
    }

    public List<OpeningHours> read() {
        return repository.findAll();
    }

    public void update(OpeningHours openingHours) throws Exception {
        val openingHoursId = openingHours.getId();
        val openingHoursById = repository.findById(openingHoursId);
        if (openingHoursById.isEmpty()) {
            throw new Exception("The update method cannot be performed! The clinic does not exist!");
        }
        repository.save(openingHours);
    }

    public void delete(Long openingHoursId) {
        repository.deleteById(openingHoursId);
    }

    public void deleteAll(List<Long> openingHoursIds) {
        repository.deleteAllByIdInBatch(openingHoursIds);
    }

    public List<OpeningHours> findAllById(List<Long> ids) {
        return repository.findAllById(ids);
    }
}

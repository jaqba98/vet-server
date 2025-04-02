package com.jakubolejarczyk.vet_server.service.independent;

import com.jakubolejarczyk.vet_server.model.independent.OpeningHour;
import com.jakubolejarczyk.vet_server.repository.independent.OpeningHoursRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OpeningHoursService {
    private final OpeningHoursRepository repository;

    public OpeningHour create(OpeningHour openingHours) {
        return repository.save(openingHours);
    }

    public Optional<OpeningHour> findById(Long id) {
        return repository.findById(id);
    }

    public List<OpeningHour> findAllById(List<Long> ids) {
        return repository.findAllById(ids);
    }

    public void updateIsArchived(List<Long> ids, Boolean isArchived) {
        repository.updateIsArchived(ids, isArchived);
    }
}

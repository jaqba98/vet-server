package com.jakubolejarczyk.vet_server.service.crud.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.model.dependent.Services;
import com.jakubolejarczyk.vet_server.repository.dependent.ServicesRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServicesService {
    private final ServicesRepository repository;

    public Services create(Services services) {
        repository.save(services);
        return services;
    }

    public List<Services> read() {
        return repository.findAll();
    }

    public Services update(Services services) throws Exception {
        val servicesById = repository.findById(services.getId());
        if (servicesById.isEmpty()) {
            throw new Exception("The update method cannot update a record that does not exist!");
        }
        repository.save(services);
        return services;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Optional<Services> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteAllByIdInBatch(List<Long> ids) {
        repository.deleteAllByIdInBatch(ids);
    }
}

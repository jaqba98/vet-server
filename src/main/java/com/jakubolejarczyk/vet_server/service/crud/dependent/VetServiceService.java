package com.jakubolejarczyk.vet_server.service.crud.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.VetService;
import com.jakubolejarczyk.vet_server.repository.dependent.VetServiceRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VetServiceService {
    private final VetServiceRepository repository;

    public VetService create(VetService vetService) {
        repository.save(vetService);
        return vetService;
    }

    public List<VetService> read() {
        return repository.findAll();
    }

    public VetService update(VetService vetService) throws Exception {
        val vetServiceById = repository.findById(vetService.getId());
        if (vetServiceById.isEmpty()) {
            throw new Exception("The update method cannot update a record that does not exist!");
        }
        repository.save(vetService);
        return vetService;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Optional<VetService> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteAllByIdInBatch(List<Long> ids) {
        repository.deleteAllByIdInBatch(ids);
    }
}

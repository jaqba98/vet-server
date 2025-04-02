package com.jakubolejarczyk.vet_server.service.base;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BaseService<TModel> {
    private final JpaRepository<TModel, Long> repository;

    public TModel save(TModel model) {
        return repository.save(model);
    }

    public List<TModel> readAll() {
        return repository.findAll();
    }

    public Optional<TModel> readById(Long id) {
        return repository.findById(id);
    }

    public void delete(TModel model) {
        repository.delete(model);
    }
}

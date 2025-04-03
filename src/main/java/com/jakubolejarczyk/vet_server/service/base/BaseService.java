package com.jakubolejarczyk.vet_server.service.base;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class BaseService<TModel, TRepository extends JpaRepository<TModel, Long>> {
    protected final TRepository repository;

    public TModel save(TModel model) {
        return repository.save(model);
    }

    public Optional<TModel> findById(Long id) {
        return repository.findById(id);
    }

    public List<TModel> findAllById(List<Long> id) {
        return repository.findAllById(id);
    }

    public void deleteAllById(List<Long> id) {
        repository.deleteAllById(id);
    }
}

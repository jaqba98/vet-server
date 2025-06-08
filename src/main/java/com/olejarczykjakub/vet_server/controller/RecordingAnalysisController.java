package com.olejarczykjakub.vet_server.controller;

import com.olejarczykjakub.vet_server.model.RecordingAnalysis;
import com.olejarczykjakub.vet_server.repository.RecordingAnalysisRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recordings")
@CrossOrigin(origins = "*")
public class RecordingAnalysisController {

    private final RecordingAnalysisRepository repository;

    public RecordingAnalysisController(RecordingAnalysisRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public RecordingAnalysis saveAnalysis(@RequestBody RecordingAnalysis analysis) {
        return repository.save(analysis);
    }

    @GetMapping
    public List<RecordingAnalysis> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecordingAnalysis> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

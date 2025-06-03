package com.olejarczykjakub.vet_server.controller;

import com.olejarczykjakub.vet_server.model.Recording;
import com.olejarczykjakub.vet_server.repository.RecordingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/recordings")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RecordingController {

    private final RecordingRepository recordingRepository;

    @Autowired
    public RecordingController(RecordingRepository recordingRepository) {
        this.recordingRepository = recordingRepository;
    }

    @PostMapping
    public ResponseEntity<Recording> createRecording(@RequestBody Recording recording) {
        if (recording.getId() == null) {
            recording.setId(UUID.randomUUID());
        }
        Recording saved = recordingRepository.save(recording);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<Recording> getAllRecordings() {
        return recordingRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recording> getRecordingById(@PathVariable UUID id) {
        Optional<Recording> recording = recordingRepository.findById(id);
        return recording.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recording> updateRecording(@PathVariable UUID id, @RequestBody Recording updatedRecording) {
        return recordingRepository.findById(id).map(recording -> {
            recording.setFileName(updatedRecording.getFileName());
            recording.setCreatedAt(updatedRecording.getCreatedAt());
            recording.setTranscript(updatedRecording.getTranscript());
            Recording saved = recordingRepository.save(recording);
            return ResponseEntity.ok(saved);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecording(@PathVariable UUID id) {
        if (recordingRepository.existsById(id)) {
            recordingRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

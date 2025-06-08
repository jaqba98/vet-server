package com.olejarczykjakub.vet_server.repository;

import com.olejarczykjakub.vet_server.model.RecordingAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordingAnalysisRepository extends JpaRepository<RecordingAnalysis, Long> {
}
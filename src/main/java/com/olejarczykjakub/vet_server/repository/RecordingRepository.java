package com.olejarczykjakub.vet_server.repository;

import com.olejarczykjakub.vet_server.model.Recording;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecordingRepository extends JpaRepository<Recording, UUID> {
}

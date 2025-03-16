package com.jakubolejarczyk.vet_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T> extends JpaRepository<T, Long> {
}

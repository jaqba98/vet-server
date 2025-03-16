package com.jakubolejarczyk.vet_server.repository;

import com.jakubolejarczyk.vet_server.model.Clinic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicRepository extends BaseRepository<Clinic> {
    @Query("SELECT c FROM Clinic c WHERE c.name = :name")
    List<Clinic> findByName(@Param("name") String name);
}

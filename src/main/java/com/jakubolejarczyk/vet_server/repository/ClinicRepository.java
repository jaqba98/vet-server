package com.jakubolejarczyk.vet_server.repository;

import com.jakubolejarczyk.vet_server.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    @Query("SELECT c FROM Clinic c WHERE :field = :value")
    List<Clinic> findByField(@Param("column") String column, @Param("value") String value);
}

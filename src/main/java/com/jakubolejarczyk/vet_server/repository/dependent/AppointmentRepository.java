package com.jakubolejarczyk.vet_server.repository.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT e FROM Appointment e WHERE e.clinicId IN :clinicIds AND e.isArchived = false")
    List<Appointment> findAllByClinicIds(@Param("clinicIds") List<Long> clinicIds);
}

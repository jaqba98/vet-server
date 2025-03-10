package com.jakubolejarczyk.vet_server.service.database;

import com.jakubolejarczyk.vet_server.model.Clinic;
import com.jakubolejarczyk.vet_server.repository.ClinicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class ClinicService {
    private final ClinicRepository clinicRepository;

    public void create(String name) {
        Clinic clinic = new Clinic();
        clinic.setName(name);
        clinicRepository.save(clinic);
    }

    public ArrayList<Clinic> read() {
        return new ArrayList<>(clinicRepository.findAll());
    }

    public void delete(Long id) {
        clinicRepository.deleteById(id);
    }

    public Boolean uniqueName(String value) {
        return clinicRepository.findByName(value).isEmpty();
    }
}

package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.dto.request.controller.ClinicRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.model.Clinic;
import com.jakubolejarczyk.vet_server.service.database.ClinicService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clinic")
@AllArgsConstructor
public class ClinicController {
    private final ClinicService service;

    @PostMapping("create")
    public ResponseEntity<ResponseDto<String>> create(@Valid @RequestBody ClinicRequestDto requestDto) {
        Clinic clinic = new Clinic();
        clinic.setName(requestDto.getName());
        clinic.setStreet(requestDto.getStreet());
        clinic.setBuildingNumber(requestDto.getBuildingNumber());
        clinic.setApartmentNumber(requestDto.getApartmentNumber());
        clinic.setPostalCode(requestDto.getPostalCode());
        clinic.setCity(requestDto.getCity());
        clinic.setProvince(requestDto.getProvince());
        clinic.setCountry(requestDto.getCountry());
        clinic.setEmail(requestDto.getEmail());
        clinic.setPhoneNumber(requestDto.getPhoneNumber());
        clinic.setOpeningHoursId(requestDto.getOpeningHoursId());
        service.create(clinic);
        ResponseDto<String> responseDto = new ResponseDto<>(true, new ArrayList<>(), "A new clinic was established!");
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("read")
    public ResponseEntity<ResponseDto<ArrayList<Clinic>>> read(@Valid @RequestBody ClinicRequestDto requestDto) {
        ArrayList<Clinic> clinics = service.read();
        ResponseDto<ArrayList<Clinic>> responseDto = new ResponseDto<>(true, new ArrayList<>(), clinics);
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("update")
    public ResponseEntity<ResponseDto<String>> update(@Valid @RequestBody ClinicRequestDto requestDto) {
        Long id = requestDto.getId();
        String name = requestDto.getName();
        Optional<Clinic> clinic = service.findById(id);
        if (clinic.isEmpty()) {
            var errors = new ArrayList<String>();
            ResponseDto<String> responseDto = new ResponseDto<>(false, errors, "The clinic has not been updated!");
            return ResponseEntity.ok().body(responseDto);
        }
        Clinic newClinic = clinic.get();
        newClinic.setId(id);
        newClinic.setName(name);
        service.update(newClinic);
        ResponseDto<String> responseDto = new ResponseDto<>(true, new ArrayList<>(), "The clinic has been updated!!");
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("delete")
    public ResponseEntity<ResponseDto<String>> delete(@Valid @RequestBody ClinicRequestDto requestDto) {
        Long id = requestDto.getId();
        service.delete(id);
        ResponseDto<String> responseDto = new ResponseDto<>(true, new ArrayList<>(), "The clinic has been deleted!!");
        return ResponseEntity.ok().body(responseDto);
    }
}

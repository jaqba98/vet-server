package com.jakubolejarczyk.vet_server.service.step_old;

import com.jakubolejarczyk.vet_server.dto.request.controller.ClinicCreateRequestDto;
import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.service.crud.dependent.ClinicService;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateClinicStep {
    private final ClinicService clinicService;

    public StepModel<Clinic> runStep(ResponseStep responseStep, ClinicCreateRequestDto requestDto, Long openingHoursId) {
        try {
            val clinic = clinicService.create(com.jakubolejarczyk.vet_server.model.dependent.Clinic.builder()
                    .name(requestDto.getName())
                    .street(requestDto.getStreet())
                    .buildingNumber(requestDto.getBuildingNumber())
                    .apartmentNumber(requestDto.getApartmentNumber())
                    .postalCode(requestDto.getPostalCode())
                    .city(requestDto.getCity())
                    .province(requestDto.getProvince())
                    .country(requestDto.getCountry())
                    .email(requestDto.getEmail())
                    .phoneNumber(requestDto.getPhoneNumber())
                    .isArchived(requestDto.getIsArchived())
                    .openingHoursId(openingHoursId)
                    .build());
            return StepModel.<Clinic>builder()
                    .error(false)
                    .data(clinic)
                    .build();
        } catch (Exception e) {
            responseStep.addMessage("Failed to create clinic!");
            return StepModel.<Clinic>builder()
                    .error(true)
                    .data(com.jakubolejarczyk.vet_server.model.dependent.Clinic.builder().build())
                    .build();
        }
    }
}

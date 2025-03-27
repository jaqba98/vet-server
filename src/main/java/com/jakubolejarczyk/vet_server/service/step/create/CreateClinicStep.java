package com.jakubolejarczyk.vet_server.service.step.create;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.service.crud.dependent.ClinicService;
import com.jakubolejarczyk.vet_server.service.crud.independent.OpeningHoursService;
import com.jakubolejarczyk.vet_server.service.input.CreateClinicInput;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import com.jakubolejarczyk.vet_server.service.output.StepOutput;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateClinicStep implements StepModel<CreateClinicInput, Clinic> {
    private final OpeningHoursService openingHoursService;
    private final ClinicService clinicService;

    @Override
    public StepOutput<Clinic> runStep(CreateClinicInput input) {
        try {
            val openingHours = openingHoursService.findById(input.openingHoursId());
            if (openingHours.isEmpty()) {
                return StepOutput.<Clinic>builder()
                        .success(false)
                        .message("Failed to create clinic. Opening hours do not exist!")
                        .build();
            }
            val clinic = Clinic.builder()
                    .name(input.name())
                    .street(input.street())
                    .buildingNumber(input.buildingNumber())
                    .apartmentNumber(input.apartmentNumber())
                    .postalCode(input.postalCode())
                    .city(input.city())
                    .province(input.province())
                    .country(input.country())
                    .email(input.email())
                    .phoneNumber(input.phoneNumber())
                    .isArchived(false)
                    .openingHoursId(input.openingHoursId())
                    .build();
            val newClinic = clinicService.create(clinic);
            return StepOutput.<Clinic>builder()
                    .success(true)
                    .data(newClinic)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

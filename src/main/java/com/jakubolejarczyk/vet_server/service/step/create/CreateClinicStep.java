package com.jakubolejarczyk.vet_server.service.step.create;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.service.crud.dependent.ClinicService;
import com.jakubolejarczyk.vet_server.service.crud.independent.OpeningHoursService;
import com.jakubolejarczyk.vet_server.service.input.create.CreateClinicInput;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import com.jakubolejarczyk.vet_server.service.model.StepOutput;
import com.jakubolejarczyk.vet_server.service.output.create.CreateClinicOutput;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateClinicStep implements StepModel<CreateClinicInput, CreateClinicOutput> {
    private final OpeningHoursService openingHoursService;
    private final ClinicService clinicService;

    @Override
    public StepOutput<CreateClinicOutput> runStep(CreateClinicInput input) {
        try {
            val openingHours = openingHoursService.findById(input.openingHoursId());
            if (openingHours.isEmpty()) {
                val output = new CreateClinicOutput(Clinic.builder().build());
                return new StepOutput<>(false, "Failed to create clinic.", output);
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
            val output = new CreateClinicOutput(newClinic);
            return new StepOutput<>(true, "The clinic has been established", output);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

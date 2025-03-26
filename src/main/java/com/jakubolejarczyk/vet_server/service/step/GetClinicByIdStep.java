package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.service.crud.dependent.ClinicService;
import com.jakubolejarczyk.vet_server.service.model.StepResponse;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetClinicByIdStep {
    private final ClinicService clinicService;

    public StepResponse<Clinic> runStep(ResponseStep responseStep, Long clinicId) {
        val clinics = clinicService.findById(clinicId);
        if (clinics.isPresent()) {
            return StepResponse.<Clinic>builder()
                    .error(false)
                    .data(clinics.get())
                    .build();
        }
        responseStep.addMessage("Failed to get clinic by ID!");
        return StepResponse.<Clinic>builder()
                .error(false)
                .data(Clinic.builder().build())
                .build();
    }
}

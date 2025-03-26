package com.jakubolejarczyk.vet_server.service.step_old;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.service.crud.dependent.ClinicService;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetClinicByIdStep {
    private final ClinicService clinicService;

    public StepModel<Clinic> runStep(ResponseStep responseStep, Long clinicId) {
        val clinics = clinicService.findById(clinicId);
        if (clinics.isPresent()) {
            return StepModel.<Clinic>builder()
                    .error(false)
                    .data(clinics.get())
                    .build();
        }
        responseStep.addMessage("Failed to get clinic by ID!");
        return StepModel.<Clinic>builder()
                .error(false)
                .data(com.jakubolejarczyk.vet_server.model.dependent.Clinic.builder().build())
                .build();
    }
}

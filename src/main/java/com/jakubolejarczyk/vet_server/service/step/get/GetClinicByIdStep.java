package com.jakubolejarczyk.vet_server.service.step.get;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.service.crud.dependent.ClinicService;
import com.jakubolejarczyk.vet_server.service.input.get.GetClinicByIdInput;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import com.jakubolejarczyk.vet_server.service.output.StepOutput;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetClinicByIdStep implements StepModel<GetClinicByIdInput, Clinic> {
    private final ClinicService clinicService;

    @Override
    public StepOutput<Clinic> runStep(GetClinicByIdInput input) {
        try {
            val clinic = clinicService.findById(input.clinicId());
            if (clinic.isPresent()) {
                return StepOutput.<Clinic>builder()
                        .success(true)
                        .data(clinic.get())
                        .build();
            }
            return StepOutput.<Clinic>builder()
                    .success(false)
                    .message("Failed to get clinic!")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.service.crud.dependent.ClinicService;
import com.jakubolejarczyk.vet_server.service.model.StepResponse;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class GetClinicByIdsStep {
    private final ClinicService clinicService;

    public StepResponse<ArrayList<Clinic>> runStep(ResponseStep responseStep, ArrayList<Long> clinicIds) {
        try {
            val clinics = new ArrayList<>(clinicService.findAllByIds(clinicIds));
            return StepResponse.<ArrayList<Clinic>>builder()
                    .error(false)
                    .data(clinics)
                    .build();
        } catch (Exception e) {
            responseStep.addMessage("Failed to get clinics by IDs!");
            return StepResponse.<ArrayList<Clinic>>builder()
                    .error(true)
                    .data(new ArrayList<>())
                    .build();
        }
    }
}

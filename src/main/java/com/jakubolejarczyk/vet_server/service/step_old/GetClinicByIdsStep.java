package com.jakubolejarczyk.vet_server.service.step_old;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.service.crud.dependent.ClinicService;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class GetClinicByIdsStep {
    private final ClinicService clinicService;

    public StepModel<ArrayList<Clinic>> runStep(ResponseStep responseStep, ArrayList<Long> clinicIds) {
        try {
            val clinics = new ArrayList<>(clinicService.findAllByIds(clinicIds));
            return StepModel.<ArrayList<Clinic>>builder()
                    .error(false)
                    .data(clinics)
                    .build();
        } catch (Exception e) {
            responseStep.addMessage("Failed to get clinics by IDs!");
            return StepModel.<ArrayList<Clinic>>builder()
                    .error(true)
                    .data(new ArrayList<>())
                    .build();
        }
    }
}

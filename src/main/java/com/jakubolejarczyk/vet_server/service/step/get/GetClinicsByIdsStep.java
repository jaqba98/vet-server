package com.jakubolejarczyk.vet_server.service.step.get;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.service.crud.dependent.ClinicService;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import com.jakubolejarczyk.vet_server.service.model.StepOutput;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetClinicsByIdsStep implements StepModel<List<Long>, List<Clinic>> {
    private final ClinicService clinicService;

    @Override
    public StepOutput<List<Clinic>> runStep(List<Long> clinicsIds) {
        try {
            val clinics = clinicService.findAllById(clinicsIds);
            return StepOutput.<List<Clinic>>builder()
                    .success(true)
                    .output(clinics)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

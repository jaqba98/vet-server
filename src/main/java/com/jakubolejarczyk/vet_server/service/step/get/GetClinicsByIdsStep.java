package com.jakubolejarczyk.vet_server.service.step.get;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.service.crud.dependent.ClinicService;
import com.jakubolejarczyk.vet_server.service.input.GetClinicsByIdsInput;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import com.jakubolejarczyk.vet_server.service.output.StepOutput;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetClinicsByIdsStep implements StepModel<GetClinicsByIdsInput, List<Clinic>> {
    private final ClinicService clinicService;

    @Override
    public StepOutput<List<Clinic>> runStep(GetClinicsByIdsInput input) {
        try {
            val clinics = clinicService.findAllById(input.clinicsIds());
            return StepOutput.<List<Clinic>>builder()
                    .success(true)
                    .data(clinics)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

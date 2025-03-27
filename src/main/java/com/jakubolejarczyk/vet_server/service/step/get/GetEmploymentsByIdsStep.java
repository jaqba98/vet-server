package com.jakubolejarczyk.vet_server.service.step.get;

import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.service.crud.dependent.EmploymentService;
import com.jakubolejarczyk.vet_server.service.input.get.GetEmploymentsByIdsInput;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import com.jakubolejarczyk.vet_server.service.output.StepOutput;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetEmploymentsByIdsStep implements StepModel<GetEmploymentsByIdsInput, List<Employment>> {
    private final EmploymentService employmentService;

    @Override
    public StepOutput<List<Employment>> runStep(GetEmploymentsByIdsInput input) {
        try {
            val employments = employmentService.findAllById(input.employmentsIds());
            return StepOutput.<List<Employment>>builder()
                    .success(true)
                    .data(employments)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
